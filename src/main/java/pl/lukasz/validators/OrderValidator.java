package pl.lukasz.validators;

import java.util.ArrayList;
import java.util.List;
import pl.lukasz.model.Buyer;
import pl.lukasz.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

  public static final int BUYER_NAME_MAX_LENGTH = 255;
  public static final int ORDER_DESCRIPTION_MAX_LENGTH = 255;
  public static final int BUYER_SURNAME_MAX_LENGTH = 255;

  private static final String TOO_LONG_BUYER_SURNAME = "Buyer surname can be maximum %d characters long";
  private static final String TOO_LONG_BUYER_NAME = "Buyer name can be maximum %d characters long";
  private static final String TOO_LONG_ORDER_DESCRIPTION = "Order description can be maximum %d characters long";

  public static final String TOO_LONG_ORDER_BUYER_NAME_MESSAGE = String.format(TOO_LONG_BUYER_NAME, BUYER_NAME_MAX_LENGTH);
  public static final String TOO_LONG_ORDER_BUYER_SURNAME_MESSAGE = String.format(TOO_LONG_BUYER_SURNAME, BUYER_SURNAME_MAX_LENGTH);
  public static final String TOO_LONG_ORDER_DESCRIPTION_MESSAGE = String.format(TOO_LONG_ORDER_DESCRIPTION, ORDER_DESCRIPTION_MAX_LENGTH);

  public static final String ORDER_DESCRIPTION_EMPTY = "Order description is empty or contains only white characters";
  public static final String BUYER_NAME_EMPTY = "Buyer name is empty or contains only white characters";
  public static final String BUYER_SURNAME_EMPTY = "Buyer surname is empty or contains only white characters";
  public static final String EMPTY_ORDER_DATE = "Order date is empty";

  @Data
  private static class ValidationParams {

    private final String input;
    private final int maxLengthAllowedForInput;
    private final String errorMessageWhenInputEmpty;
    private final String errorMessageWhenInputTooLong;
  }

  public List<String> validate(Order order) {
    List<String> validationResults = new ArrayList<>();
    validateOrderDescription(order, validationResults);
    validateBuyer(order.getBuyer(), validationResults);
    validateOrderDate(order, validationResults);

    return validationResults;
  }

  private void validateOrderDescription(Order order, List<String> validationResults) {
    final ValidationParams orderDescriptionValidationParams = new ValidationParams(
        order.getDescription(),
        ORDER_DESCRIPTION_MAX_LENGTH,
        ORDER_DESCRIPTION_EMPTY,
        TOO_LONG_ORDER_DESCRIPTION_MESSAGE);

    validateString(orderDescriptionValidationParams, validationResults);
  }

  private void validateBuyer(Buyer buyer, List<String> validationResults) {
    if (isNullOrEmpty(buyer.getName())) {
      validationResults.add(BUYER_NAME_EMPTY);
    }
    if (isNullOrEmpty(buyer.getSurname())) {
      validationResults.add(BUYER_SURNAME_EMPTY);
    } else {
      validateLength(buyerSurname, BUYER_SURNAME_MAX_LENGTH, TOO_LONG_ORDER_BUYER_SURNAME_MESSAGE, validationResults);
    }
  }

  private void validateString(ValidationParams validationParams, List<String> validationResults) {
    if (isNullOrEmpty(validationParams.getInput())) {
      validationResults.add(validationParams.getErrorMessageWhenInputEmpty());
    } else {
      validateLength(validationParams.getInput(), validationParams.getMaxLengthAllowedForInput(),
          validationParams.getErrorMessageWhenInputTooLong(), validationResults);
    }
  }

  private void validateOrderDate(Order order, List<String> validationResults) {
    if (order.getDate() == null) {
      validationResults.add(EMPTY_ORDER_DATE);
    }
  }

  private boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

  private void validateLength(String input, int maxLengthAllowedForInput, String onTooLongMessage, List<String> validationResults) {
    if (input.length() > maxLengthAllowedForInput) {
      validationResults.add(onTooLongMessage);
    }
  }

}
