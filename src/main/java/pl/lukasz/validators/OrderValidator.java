package pl.lukasz.validators;

import java.util.ArrayList;
import java.util.List;
import pl.lukasz.model.Buyer;
import pl.lukasz.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

  public static final String ORDER_DESCRIPTION_EMPTY = "Order description is empty or contains only white characters";
  public static final String BUYER_NAME_EMPTY = "Buyer name is empty or contains only white characters";
  public static final String BUYER_SURNAME_EMPTY = "Buyer surname is empty or contains only white characters";
  public static final String EMPTY_ORDER_DATE = "Order date is empty";

  public List<String> validate(Order order) {
    List<String> validationResults = new ArrayList<>();
    validateOrderDescription(order, validationResults);
    validateBuyer(order.getBuyer(), validationResults);
    validateOrderDate(order, validationResults);

    return validationResults;
  }

  private void validateOrderDescription(Order order, List<String> validationResults) {
    if (isNullOrEmpty(order.getDescription())) {
      validationResults.add(ORDER_DESCRIPTION_EMPTY);
    }
  }

  private void validateBuyer(Buyer buyer, List<String> validationResults) {
    if (isNullOrEmpty(buyer.getName())) {
      validationResults.add(BUYER_NAME_EMPTY);
    }
    if (isNullOrEmpty(buyer.getSurname())) {
      validationResults.add(BUYER_SURNAME_EMPTY);
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

}
