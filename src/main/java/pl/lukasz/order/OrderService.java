package pl.lukasz.order;

import java.util.Collection;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lukasz.model.Buyer;
import pl.lukasz.model.Order;

@AllArgsConstructor
@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public Order addOrder(Order order) {
    return orderRepository.save(order);
  }

  public Optional<Order> getOrderById(long id) {
    return orderRepository.getOrderById(id);
  }

  public Collection<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public void updateOrder(long id, Order updatedOrder) {
    Order orderToUpdate = getOrderFromDatabase(id);
    orderToUpdate.setDescription(updatedOrder.getDescription());
    Buyer buyerToUpdate = updatedOrder.getBuyer();
    buyerToUpdate.setName(updatedOrder.getBuyer().getName());
    buyerToUpdate.setSurname(updatedOrder.getBuyer().getSurname());
    orderToUpdate.setBuyer(buyerToUpdate);
    orderToUpdate.setDate(updatedOrder.getDate());
    orderRepository.save(orderToUpdate);
  }

  public void deleteOrder(long id) {
    orderRepository.deleteById(id);
  }

  private Order getOrderFromDatabase(long id) {
    Optional<Order> orderOptional = orderRepository.getOrderById(id);
    if (orderOptional.isEmpty()) {
      throw new IllegalStateException("Order with id: " + id + " does not exist in database");
    }
    return orderOptional.get();
  }
}
