package pl.lukasz.order;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lukasz.helpers.TestOrderProvider;
import pl.lukasz.model.Order;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @InjectMocks
  private OrderService orderService;

  private Order order1;
  private Order order2;

  @BeforeEach
  void setUp() {
    TestOrderProvider testOrderProvider = new TestOrderProvider();
    order1 = testOrderProvider.getRandomOrder();
    order2 = testOrderProvider.getRandomOrder();
  }

  @Test
  void shouldReturnOrderById() {
    // given
    when(orderRepository.getOrderById(1L)).thenReturn(Optional.ofNullable(order1));

    // when
    Optional<Order> resultOrderOptional = orderService.getOrderById(1L);

    // then
    assertThat(resultOrderOptional.isPresent(), is(true));
    assertThat(resultOrderOptional.get(), is(equalTo(order1)));
  }

  @Test
  void shouldReturnAllOrders() {
    // given
    List<Order> expectedOrderList = List.of(order1, order2);
    when(orderRepository.findAll()).thenReturn(expectedOrderList);

    // when
    Collection<Order> actualOrderList = orderService.getAllOrders();

    // then
    assertThat(actualOrderList, is(equalTo(expectedOrderList)));
    verify(orderRepository).findAll();
  }

  @Test
  void shouldSaveOrder() {
    // given
    when(orderRepository.save(order1)).thenReturn(order1);

    // when
    Order actualOrder = orderService.addOrder(order1);

    // then
    assertThat(actualOrder, is(equalTo(order1)));
    verify(orderRepository).save(order1);
  }

  @Test
  void shouldUpdateOrder() {
    // given
    Order originalOrder = order1.withId(1);
    Order orderToUpdate = order2.withId(2);
    Order updatedOrder = orderToUpdate.withId(originalOrder.getId());

    when(orderRepository.getOrderById(originalOrder.getId())).thenReturn(Optional.of(originalOrder));
    when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

    // when
    orderService.updateOrder(originalOrder.getId(), orderToUpdate);

    // then
    verify(orderRepository).getOrderById((originalOrder.getId()));
    verify(orderRepository).save(orderToUpdate.withId(originalOrder.getId()));
  }

  @Test
  void shouldThrowExceptionWhenTryingToUpdateNotExistingOrder() {
    // given
    Order originalOrder = order1.withId(1);
    Order orderToUpdate = order2.withId(2);

    when(orderRepository.getOrderById(originalOrder.getId())).thenReturn(Optional.empty());

    // when
    Throwable exception = assertThrows(IllegalStateException.class, () -> orderService.updateOrder(originalOrder.getId(), orderToUpdate));

    // then
    assertThat(exception.getMessage(), is(equalTo("Order with id: " + originalOrder.getId() + " does not exist in database")));
    verify(orderRepository).getOrderById((originalOrder.getId()));
    verify(orderRepository, never()).save(orderToUpdate.withId(originalOrder.getId()));
  }

  @Test
  void shouldDeleteOrder() {
    // given
    doNothing().when(orderRepository).deleteById(1L);

    // when
    orderService.deleteOrder(1L);

    // then
    verify(orderRepository).deleteById(1L);
  }
}
