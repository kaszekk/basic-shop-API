package pl.lukasz;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasz.model.Order;
import pl.lukasz.validators.OrderValidator;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController implements OrderApi {

  private final OrderValidator orderValidator;
  private final OrderService orderService;

  @Override
  public ResponseEntity<?> addOrder(@RequestBody Order order) {
    log.info("Adding order to the database");
    List<String> validationResult = orderValidator.validate(order);

    if (!validationResult.isEmpty()) {
      log.info("Order is not valid {}", validationResult);
      return ResponseEntity.badRequest().body(validationResult);
    }
    return ResponseEntity.ok(orderService.addOrder(order).getId());

  }

  @Override
  public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody Order updatedOrder) {
    Optional<Order> orderOptional = orderService.getOrderById(id);

    if (orderOptional.isEmpty()) {
      log.info("No order with id {} was found, not able to update", id);
      return ResponseEntity.notFound().build();
    }
    List<String> validationResult = orderValidator.validate(updatedOrder);

    if (!validationResult.isEmpty()) {
      log.info("Order is not valid {}", validationResult);
      return ResponseEntity.badRequest().body(validationResult);
    }
    orderService.updateOrder(id, updatedOrder);
    log.info("Order with id {} was successfully updated", id);

    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<?> deleteOrder(@PathVariable long id) {
    Optional<Order> orderOptional = orderService.getOrderById(id);

    if (orderOptional.isEmpty()) {
      log.info("No order with id {} was found, not able to delete", id);
      return ResponseEntity.notFound().build();
    }
    log.info("Attempting to delete order with id {}", id);

    orderService.deleteOrder(id);
    log.info("Order with id {} was deleted successfully", id);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Collection<Order>> getAllOrders() {
    log.info("Getting all orders from database");
    return ResponseEntity.ok().body(orderService.getAllOrders());
  }

  @Override
  public ResponseEntity<?> getOrderById(@PathVariable long id) {
    log.info("Getting order with id {}", id);
    Optional<Order> orderOptional = orderService.getOrderById(id);

    if (orderOptional.isEmpty()) {
      log.info("Order with id {} was not found", id);
      return ResponseEntity.notFound().build();
    }

    log.info("Order with id {} was successfully retrieved", id);
    return ResponseEntity.ok(orderOptional.get());
  }
}
