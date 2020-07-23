package pl.lukasz.order;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Collection;
import pl.lukasz.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("order")
@ApiOperation(value = "/order", notes = "Available operations for Simple-shop-API application", tags = {"Order"})
public interface OrderApi {

  @ApiOperation(value = "Create a new order", notes = "Add an order to database", response = Long.class)
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Order to add has not passed validation and has not been added to database.")})
  @PostMapping
  ResponseEntity<?> addOrder(Order order);

  @ApiOperation(value = "Update an existing order", notes = "Update an order with given id in database, with other order - passed in "
      + "request", response = Void.class)
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Order passed in request with intention to update existing order with given id - has not passed "
          + "validation, update not possible."),
      @ApiResponse(code = 404, message = "Order with passed id does not exist in database, update not possible")})
  @PutMapping(value = "/{id}")
  ResponseEntity<?> updateOrder(@PathVariable long id, Order order);

  @ApiOperation(value = "Delete an existing order", notes = "Delete an order with given id from database", response = Void.class)
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Order with passed id does not exist in database, deletion not possible")})
  @DeleteMapping(value = "/{id}")
  ResponseEntity<?> deleteOrder(@PathVariable long id);

  @ApiOperation(value = "Get collection of all orders", notes = "Get all orders from database", response = Order.class, responseContainer = "List")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK")})
  @GetMapping
  ResponseEntity<Collection<Order>> getAllOrders();

  @ApiOperation(value = "Get a single order", notes = "Get an order by id", response = Order.class)
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Order not found for given id.")})
  @GetMapping(value = "/{id}")
  ResponseEntity<?> getOrderById(@PathVariable long id);
}
