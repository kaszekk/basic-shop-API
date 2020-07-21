package pl.lukasz.validators;

import static org.junit.Assert.assertEquals;
import static pl.lukasz.validators.OrderValidator.BUYER_NAME_EMPTY;
import static pl.lukasz.validators.OrderValidator.BUYER_SURNAME_EMPTY;
import static pl.lukasz.validators.OrderValidator.ORDER_DESCRIPTION_EMPTY;

import java.time.LocalDate;
import java.util.List;
import pl.lukasz.model.Buyer;
import pl.lukasz.model.Order;
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
  public void shouldReturnValidationErrorForOrderWithEmptyNameBuyer() {
    // given
    Buyer emptyNameBuyer = Buyer.builder()
        .name(EMPTY)
        .surname("Smith")
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .buyer(emptyNameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(BUYER_NAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithWhiteCharactersNameBuyer() {
    // given
    Buyer whiteCharactersNameBuyer = Buyer.builder()
        .name(ONLY_WHITE_CHARACTERS)
        .surname("Smith")
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .buyer(whiteCharactersNameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(BUYER_NAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithNullNameBuyer() {
    // given
    Buyer nullNameBuyer = Buyer.builder()
        .name(null)
        .surname("Smith")
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .buyer(nullNameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(BUYER_NAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithEmptySurnameBuyer() {
    // given
    Buyer emptySurnameBuyer = Buyer.builder()
        .name("Adam")
        .surname(EMPTY)
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .buyer(emptySurnameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(BUYER_SURNAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithWhiteCharactersSurnameBuyer() {
    // given
    Buyer whiteCharactersSurnameBuyer = Buyer.builder()
        .name("Adam")
        .surname(ONLY_WHITE_CHARACTERS)
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .buyer(whiteCharactersSurnameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(BUYER_SURNAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithNullSurnameBuyer() {
    // given
    Buyer nullSurnameBuyer = Buyer.builder()
        .name("Adam")
        .surname(null)
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .buyer(nullSurnameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(BUYER_SURNAME_EMPTY, validationResult.get(0));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithEmptyDescription() {
    // given
    Buyer buyer = Buyer.builder()
        .name("Adam")
        .surname("Smith")
        .build();

    Order emptyDescriptionOrder = Order.builder()
        .description(EMPTY)
        .buyer(buyer)
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
    Buyer buyer = Buyer.builder()
        .name("Adam")
        .surname("Smith")
        .build();

    Order whiteCharactersDescriptionOrder = Order.builder()
        .description(ONLY_WHITE_CHARACTERS)
        .buyer(buyer)
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
    Buyer buyer = Buyer.builder()
        .name("Adam")
        .surname("Smith")
        .build();

    Order nullDescriptionOrder = Order.builder()
        .description(null)
        .buyer(buyer)
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
    Buyer nullSurnameBuyer = Buyer.builder()
        .name("Adam")
        .surname(null)
        .build();

    Order order = Order.builder()
        .description("Pizza")
        .buyer(nullSurnameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertEquals(1, validationResult.size());
    assertEquals(BUYER_SURNAME_EMPTY, validationResult.get(0));
  }

}
