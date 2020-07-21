package validators;

import static org.junit.Assert.assertEquals;
import static validators.OrderValidator.CUSTOMER_NAME_EMPTY;
import static validators.OrderValidator.CUSTOMER_SURNAME_EMPTY;
import static validators.OrderValidator.ORDER_DESCRIPTION_EMPTY;

import java.time.LocalDate;
import java.util.List;
import model.Customer;
import model.Order;
import org.junit.Before;
import org.junit.Test;

public class OrderValidatorTest {

  private static final String EMPTY = "";
  private static final String ONLY_WHITE_CHARACTERS = "   ";
  private OrderValidator orderValidator;

  @Before
  public void setUp() {
    orderValidator = new OrderValidator();
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithEmptyNameCustomer() {
    // given
    Customer emptyNameCustomer = Customer.builder()
        .name(EMPTY)
        .surname("Smith")
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .customer(emptyNameCustomer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(CUSTOMER_NAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithWhiteCharactersNameCustomer() {
    // given
    Customer whiteCharactersNameCustomer = Customer.builder()
        .name(ONLY_WHITE_CHARACTERS)
        .surname("Smith")
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .customer(whiteCharactersNameCustomer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(CUSTOMER_NAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithNullNameCustomer() {
    // given
    Customer nullNameCustomer = Customer.builder()
        .name(null)
        .surname("Smith")
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .customer(nullNameCustomer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(CUSTOMER_NAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithEmptySurnameCustomer() {
    // given
    Customer emptySurnameCustomer = Customer.builder()
        .name("Adam")
        .surname(EMPTY)
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .customer(emptySurnameCustomer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(CUSTOMER_SURNAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithWhiteCharactersSurnameCustomer() {
    // given
    Customer whiteCharactersSurnameCustomer = Customer.builder()
        .name("Adam")
        .surname(ONLY_WHITE_CHARACTERS)
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .customer(whiteCharactersSurnameCustomer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(CUSTOMER_SURNAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithNullSurnameCustomer() {
    // given
    Customer nullSurnameCustomer = Customer.builder()
        .name("Adam")
        .surname(null)
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .customer(nullSurnameCustomer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(CUSTOMER_SURNAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithEmptyDescription() {
    // given
    Customer customer = Customer.builder()
        .name("Adam")
        .surname("Smith")
        .build();

    Order emptyDescriptionOrder = Order.builder()
        .description(EMPTY)
        .customer(customer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(emptyDescriptionOrder);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(ORDER_DESCRIPTION_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithWhiteCharactersDescription() {
    // given
    Customer customer = Customer.builder()
        .name("Adam")
        .surname("Smith")
        .build();

    Order whiteCharactersDescriptionOrder = Order.builder()
        .description(ONLY_WHITE_CHARACTERS)
        .customer(customer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(whiteCharactersDescriptionOrder);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(ORDER_DESCRIPTION_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithNullDescription() {
    // given
    Customer customer = Customer.builder()
        .name("Adam")
        .surname("Smith")
        .build();

    Order nullDescriptionOrder = Order.builder()
        .description(null)
        .customer(customer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(nullDescriptionOrder);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(ORDER_DESCRIPTION_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForNullDateOrder() {
    // given
    Customer nullSurnameCustomer = Customer.builder()
        .name("Adam")
        .surname(null)
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .customer(nullSurnameCustomer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(CUSTOMER_SURNAME_EMPTY, validationResult.get(0));
  }

}
