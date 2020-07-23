package pl.lukasz.order;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static pl.lukasz.validators.OrderValidator.BUYER_NAME_EMPTY;
import static pl.lukasz.validators.OrderValidator.BUYER_SURNAME_EMPTY;
import static pl.lukasz.validators.OrderValidator.ORDER_DESCRIPTION_EMPTY;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import pl.lukasz.model.Buyer;
import pl.lukasz.model.Order;

public class OrderControllerIntegrationTest extends IntegrationTestBase {

  @Test
  public void shouldReturnIdWhenOrderAddedSuccessfully() throws Exception {
    // given
    Buyer buyer = new Buyer("Franek", "Kimono");
    LocalDate orderDate = LocalDate.of(2020, 7, 20);
    Order orderRequest = new Order("Pizza", buyer, orderDate);
    long expectedId = 1;

    // when
    long actual = callRestToAddOrderAndReturnId(orderRequest);

    // then
    assertThat(actual, is(equalTo(expectedId)));
  }

  @Test
  public void shouldAddOrder() throws Exception {
    // given
    Buyer buyer = new Buyer("Franek", "Kimono");
    LocalDate orderDate = LocalDate.of(2020, 6, 20);

    Order expectedOrder = new Order("Bread", buyer, orderDate);

    // when
    final long addedOrderId = callRestToAddOrderAndReturnId(expectedOrder);
    Order addedOrder = callRestToGetOrderById(addedOrderId);
    int numberOfAllOrdersInDb = callRestToGetAllOrders().size();

    // then
    assertThat(numberOfAllOrdersInDb, is(equalTo(1)));
    assertThat(addedOrder, is(equalTo(expectedOrder.withId(addedOrderId))));
  }

  @Test
  public void shouldReturnBadRequestForValidationErrorInAddOrderMethod() throws Exception {
    // given
    Buyer emptyNameBuyer = new Buyer("", "Kimono");
    LocalDate orderDate = LocalDate.of(2020, 6, 20);

    Order invalidOrder = new Order("Bread", emptyNameBuyer, orderDate);

    // when
    final MockHttpServletResponse response = callRestToAddOrderAndReturnResponse(invalidOrder);
    final List<String> validationResult = getValidationResultFromResponse(response);

    // then
    assertThat(response.getStatus(), is(equalTo(HttpStatus.BAD_REQUEST.value())));
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(BUYER_NAME_EMPTY)));
  }

  @Test
  public void shouldReturnBadRequestForValidationErrorsInAddOrderMethod() throws Exception {
    // given
    Buyer emptyNameAndSurnameBuyer = new Buyer("", "  ");
    LocalDate orderDate = LocalDate.of(2020, 6, 20);

    Order invalidOrder = new Order("      ", emptyNameAndSurnameBuyer, orderDate);

    // when
    final MockHttpServletResponse response = callRestToAddOrderAndReturnResponse(invalidOrder);
    final List<String> validationResult = getValidationResultFromResponse(response);

    // then
    assertThat(response.getStatus(), is(equalTo(HttpStatus.BAD_REQUEST.value())));
    assertThat(validationResult.size(), is(equalTo(3)));
    assertThat(validationResult.get(0), is(equalTo(BUYER_NAME_EMPTY)));
    assertThat(validationResult.get(1), is(equalTo(BUYER_SURNAME_EMPTY)));
    assertThat(validationResult.get(2), is(equalTo(ORDER_DESCRIPTION_EMPTY)));
  }

  @Test
  public void shouldUpdateOrder() throws Exception {
    // given
    Buyer buyer = new Buyer("Franek", "Kimono");
    LocalDate orderDate = LocalDate.of(2020, 6, 20);

    Order orderToAdd = new Order("Bread", buyer, orderDate);

    final long originalOrderId = callRestToAddOrderAndReturnId(orderToAdd);
    Order addedOrder = callRestToGetOrderById(originalOrderId);
    int numberOfAllOrdersInDb = callRestToGetAllOrders().size();

    assertThat(numberOfAllOrdersInDb, is(equalTo(1)));
    assertThat(addedOrder, is(equalTo(addedOrder)));

    Buyer updatedBuyer = new Buyer("Alan", "Pogoda");
    LocalDate updatedOrderDate = LocalDate.of(2019, 2, 23);

    Order updatedOrder = new Order("Sushi", updatedBuyer, updatedOrderDate);
    // when
    final int status = callRestToUpdateOrderAndReturnStatus(originalOrderId, updatedOrder);
    assertThat(status, is(equalTo(HttpStatus.OK.value())));

    Order updatedOrderFromDb = callRestToGetOrderById(originalOrderId);

    // then
    assertThat(updatedOrderFromDb.getId(), is(equalTo(originalOrderId)));
    assertThat(updatedOrderFromDb, is(equalTo(updatedOrder.withId(originalOrderId))));
  }

  @Test
  public void shouldReturnNotFoundWhenTryingToUpdateNotExistingOrder() throws Exception {
    // given
    long notExistingId = 35622352;

    int numberOfAllOrdersInDb = callRestToGetAllOrders().size();

    assertThat(numberOfAllOrdersInDb, is(equalTo(0)));

    Buyer updatedBuyer = new Buyer("Alan", "Pogoda");
    LocalDate updatedOrderDate = LocalDate.of(2019, 2, 23);

    Order updatedOrder = new Order("Sushi", updatedBuyer, updatedOrderDate);

    // when
    final int status = callRestToUpdateOrderAndReturnStatus(notExistingId, updatedOrder);

    // then
    assertThat(status, is(equalTo(HttpStatus.NOT_FOUND.value())));
  }

  @Test
  public void shouldGetAllOrders() throws Exception {
    // given
    LocalDate pizzaOrderDate = LocalDate.of(2020, 6, 1);
    LocalDate spriteOrderDate = LocalDate.of(2020, 6, 10);
    LocalDate bazookaOrderDate = LocalDate.of(2020, 4, 11);
    LocalDate chipsOrderDate = LocalDate.of(2020, 5, 13);

    Buyer pizzaBuyer = new Buyer("Franek", "Kimono");
    Buyer spriteBuyer = new Buyer("Ferdynand", "Kiepski");
    Buyer bazookaBuyer = new Buyer("Zulu", "Gula");
    Buyer chipsBuyer = new Buyer("Justin", "Bieber");

    Order pizzaOrder = new Order("Pizza", pizzaBuyer, pizzaOrderDate);
    Order spriteOrder = new Order("Sprite", spriteBuyer, spriteOrderDate);
    Order bazookaOrder = new Order("Bazooka", bazookaBuyer, bazookaOrderDate);
    Order chipsOrder = new Order("Chips", chipsBuyer, chipsOrderDate);

    final long pizzaOrderId = callRestToAddOrderAndReturnId(pizzaOrder);
    final long spriteOrderId = callRestToAddOrderAndReturnId(spriteOrder);
    final long bazookaOrderId = callRestToAddOrderAndReturnId(bazookaOrder);
    final long chipsOrderId = callRestToAddOrderAndReturnId(chipsOrder);

    // when
    final List<Order> allOrdersFromDb = callRestToGetAllOrders();

    // then
    assertThat(allOrdersFromDb.size(), is(equalTo(4)));

    assertThat(allOrdersFromDb.get(0), is(equalTo(pizzaOrder.withId(pizzaOrderId))));
    assertThat(allOrdersFromDb.get(1), is(equalTo(spriteOrder.withId(spriteOrderId))));
    assertThat(allOrdersFromDb.get(2), is(equalTo(bazookaOrder.withId(bazookaOrderId))));
    assertThat(allOrdersFromDb.get(3), is(equalTo(chipsOrder.withId(chipsOrderId))));
  }

  @Test
  public void shouldDeleteOrder() throws Exception {
    // given
    Buyer expectedBuyer = new Buyer("John", "Bean");
    LocalDate expectedDate = LocalDate.of(2020, 3, 17);
    Order expectedOrder = new Order("vegetables", expectedBuyer, expectedDate);

    final long expectedOrderId = callRestToAddOrderAndReturnId(expectedOrder);

    Order addedOrder = callRestToGetOrderById(expectedOrderId);
    int numberOfAllOrdersInDbBeforeDeletion = callRestToGetAllOrders().size();

    assertThat(numberOfAllOrdersInDbBeforeDeletion, is(equalTo(1)));
    assertThat(addedOrder, is(equalTo(expectedOrder.withId(expectedOrderId))));

    // when
    final int status = callRestToDeleteOrderByIdAndReturnStatus(expectedOrderId);
    int numberOfAllOrdersInDbAfterDeletion = callRestToGetAllOrders().size();

    // then
    assertThat(status, is(equalTo(HttpStatus.OK.value())));
    assertThat(numberOfAllOrdersInDbAfterDeletion, is(equalTo(0)));
  }
}
