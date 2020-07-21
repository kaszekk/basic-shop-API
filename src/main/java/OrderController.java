import java.util.List;
import model.Order;
import org.springframework.http.ResponseEntity;
import validators.OrderValidator;

public class OrderController implements OrderApi {

  private OrderValidator orderValidator;

  @Override
  public ResponseEntity<?> addOrder(Order order) {
    List<String> validationResult = orderValidator.validate(order);
    throw new UnsupportedOperationException("Not implemented");

  }

  @Override
  public ResponseEntity<?> updateOrder(int id, Order order) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public ResponseEntity<?> deleteOrder(int id) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public ResponseEntity<List<Order>> getOrders() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public ResponseEntity<?> getOrderById(long id) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
