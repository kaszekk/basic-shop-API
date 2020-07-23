package pl.lukasz.validators;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.lukasz.validators.OrderValidator.BUYER_NAME_EMPTY;
import static pl.lukasz.validators.OrderValidator.BUYER_NAME_MAX_LENGTH;
import static pl.lukasz.validators.OrderValidator.BUYER_SURNAME_EMPTY;
import static pl.lukasz.validators.OrderValidator.BUYER_SURNAME_MAX_LENGTH;
import static pl.lukasz.validators.OrderValidator.ORDER_DESCRIPTION_EMPTY;
import static pl.lukasz.validators.OrderValidator.ORDER_DESCRIPTION_MAX_LENGTH;
import static pl.lukasz.validators.OrderValidator.TOO_LONG_ORDER_BUYER_NAME_MESSAGE;
import static pl.lukasz.validators.OrderValidator.TOO_LONG_ORDER_BUYER_SURNAME_MESSAGE;
import static pl.lukasz.validators.OrderValidator.TOO_LONG_ORDER_DESCRIPTION_MESSAGE;

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
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(BUYER_NAME_EMPTY)));
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
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(BUYER_NAME_EMPTY)));
  }

  @Test
  public void shouldReturnValidationErrorForTooLongNameBuyer() {
    // given
    String tooLongName = "d".repeat(BUYER_NAME_MAX_LENGTH + 1);
    Buyer tooLongNameBuyer = new Buyer(tooLongName, "Smith");

    Order order = Order.builder()
        .description("Pizza")
        .buyer(tooLongNameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(TOO_LONG_ORDER_BUYER_NAME_MESSAGE)));
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
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(BUYER_SURNAME_EMPTY)));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithWhiteCharactersSurnameBuyer() {
    // given
    Buyer whiteCharactersSurnameBuyer = new Buyer("Adam", ONLY_WHITE_CHARACTERS);

    Order order = Order.builder()
        .description("Pizza")
        .buyer(whiteCharactersSurnameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(BUYER_SURNAME_EMPTY)));
  }

  @Test
  public void shouldReturnValidationErrorForTooLongSurnameBuyer() {
    // given
    String tooLongSurname = "s".repeat(BUYER_SURNAME_MAX_LENGTH + 1);
    Buyer tooLongSurnameBuyer = new Buyer("Frank", tooLongSurname);

    Order order = Order.builder()
        .description("Pizza")
        .buyer(tooLongSurnameBuyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(TOO_LONG_ORDER_BUYER_SURNAME_MESSAGE)));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithEmptyDescription() {
    // given
    Buyer buyer = new Buyer("Adam", "Smith");

    Order emptyDescriptionOrder = Order.builder()
        .description(EMPTY)
        .buyer(buyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(emptyDescriptionOrder);

    // then
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(ORDER_DESCRIPTION_EMPTY)));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithWhiteCharactersDescription() {
    // given
    Buyer buyer = new Buyer("Adam", "Smith");

    Order whiteCharactersDescriptionOrder = Order.builder()
        .description(ONLY_WHITE_CHARACTERS)
        .buyer(buyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(whiteCharactersDescriptionOrder);

    // then
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(ORDER_DESCRIPTION_EMPTY)));
  }

  @Test
  public void shouldThrowExceptionForOrderWithNullDescription() {
    // given
    Buyer buyer = new Buyer("Adam", "Smith");

    // when
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      Order.builder()
          .description(null)
          .buyer(buyer)
          .date(LocalDate.of(2020, 7, 20))
          .build();
    });

    assertThat(exception.getMessage(), is(equalTo("order description cannot be null")));

  }

  @Test
  public void shouldReturnValidationErrorForTooLongOrderDescription() {
    // given
    String tooLongDescription = "d".repeat(ORDER_DESCRIPTION_MAX_LENGTH + 1);
    Buyer buyer = new Buyer("Alan", "Smith");

    Order order = Order.builder()
        .description(tooLongDescription)
        .buyer(buyer)
        .date(LocalDate.of(2020, 7, 20))
        .build();

    // when
    final List<String> validationResult = orderValidator.validate(order);

    // then
    assertThat(validationResult.size(), is(equalTo(1)));
    assertThat(validationResult.get(0), is(equalTo(TOO_LONG_ORDER_DESCRIPTION_MESSAGE)));
  }

  @Test
  public void shouldThrowExceptionForOrderWithNullNameBuyer() {
    // given
    Buyer nullNameBuyer = new Buyer(null, "Smith");

    // when
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      Order.builder()
          .description("Pizza")
          .buyer(nullNameBuyer)
          .date(LocalDate.of(2020, 7, 20))
          .build();
    });

    assertThat(exception.getMessage(), is(equalTo("buyer name cannot be null")));
  }

  @Test
  public void shouldThrowExceptionForOrderWithNullBuyer() {
    // when
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      Order.builder()
          .description("Pizza")
          .buyer(null)
          .date(LocalDate.of(2020, 7, 20))
          .build();
    });

    assertThat(exception.getMessage(), is(equalTo("buyer cannot be null")));
  }

  @Test
  public void shouldReturnValidationErrorForOrderWithNullSurnameBuyer() {
    // given
    Buyer nullSurnameBuyer = new Buyer("Adam", null);

    // when
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      Order order = Order.builder()
          .description("Pizza")
          .buyer(nullSurnameBuyer)
          .date(LocalDate.of(2020, 7, 20))
          .build();
    });

    assertThat(exception.getMessage(), is(equalTo("buyer surname cannot be null")));
  }
}
