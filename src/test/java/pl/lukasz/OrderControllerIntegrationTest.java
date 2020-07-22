package pl.lukasz;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.lukasz.model.Buyer;
import pl.lukasz.model.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderControllerIntegrationTest extends IntegrationTestBase {

  @Test
  public void shouldReturnIdWhenEmployeeAddedSuccessfully() throws Exception {
    //Given
    Buyer buyer = new Buyer("Franek", "Kimono");
    LocalDate orderDate = LocalDate.of(2020, 7, 20);
    Order orderRequest = new Order("Pizza", buyer, orderDate);
    long expectedId = 1;

    //When
    long actual = callRestToAddOrderAndReturnId(orderRequest);

    //Then
    assertThat(actual, is(equalTo(expectedId)));
  }

  @Test
  public void shouldAddOrder() throws Exception {
    //Given
    Buyer buyer = new Buyer("Franek", "Kimono");
    LocalDate orderDate = LocalDate.of(2020, 6, 20);

    Order expectedOrder = new Order("John", buyer, orderDate);
    expectedOrder.setId(1L);

    //When
    final long addedOrderId = callRestToAddOrderAndReturnId(expectedOrder);
    Order addedOrder = callRestToGetOrderById(addedOrderId);
    int numberOfAllOrdersInDb = callRestToGetAllOrders().size();

    //Then
    assertThat(numberOfAllOrdersInDb, is(equalTo(1)));
    assertThat(addedOrder, is(equalTo(expectedOrder)));
  }

}
