package validators;

import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

  public static final String ORDER_DESCRIPTION_EMPTY = "Order description is empty or contains only white characters";
  public static final String CUSTOMER_NAME_EMPTY = "Customer name is empty or contains only white characters";
  public static final String CUSTOMER_SURNAME_EMPTY = "Customer surname is empty or contains only white characters";
  public static final String EMPTY_ORDER_DATE = "Order date is empty";

  public List<String> validate(Order order) {
    List<String> validationResults = new ArrayList<>();
    validateOrderDescription(order, validationResults);
    validateCustomer(order.getCustomer(), validationResults);
    validateOrderDate(order, validationResults);

    return validationResults;
  }

  private void validateOrderDescription(Order order, List<String> validationResults) {
    if (isNullOrEmpty(order.getDescription())) {
      validationResults.add(ORDER_DESCRIPTION_EMPTY);
    }
  }

  private void validateCustomer(Customer customer, List<String> validationResults) {
    if (isNullOrEmpty(customer.getName())) {
      validationResults.add(CUSTOMER_NAME_EMPTY);
    }
    if (isNullOrEmpty(customer.getSurname())) {
      validationResults.add(CUSTOMER_SURNAME_EMPTY);
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
