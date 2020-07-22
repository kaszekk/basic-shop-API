package pl.lukasz;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.lukasz.model.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderControllerIntegrationTest extends IntegrationTestBase {

  @Test
  public void shouldReturnIdWhenEmployeeAddedSuccessfully() throws Exception {
    //Given
    LocalDate orderDate = LocalDate.of(2020, 7, 20);
    Order orderRequest = new Order("Pizza", "Franek", "Kimono", orderDate);
    long expectedId = 1;

    //When
    long actual = callRestToAddOrderAndReturnId(orderRequest);

    //Then
    assertEquals(expectedId, actual);
  }



  @Test
  public void shouldAddOrder() throws Exception {
    //Given
    LocalDate orderDate = LocalDate.of(2020, 6, 20);

    Order expectedOrder = new Order("John", "Doe", "Frisk", orderDate);
    expectedOrder.setId(1L);

    //When
    final long addedOrderId = callRestToAddOrderAndReturnId(expectedOrder);
    Order addedOrder = callRestToGetOrderById(addedOrderId);
    int numberOfAllOrdersInDb = callRestToGetAllOrders().size();

    //Then
    assertEquals(1, numberOfAllOrdersInDb);
    assertEquals(expectedOrder, addedOrder);
  }

}
