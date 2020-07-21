import java.util.List;
import java.util.Optional;
import model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> getOrderById(long id);

  List<Order> getAllOrders();
}
