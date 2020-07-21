import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Customer;

public class TestCustomerProvider {

  public static Customer pickRandomCustomerFromCustomers() {
    int randomListIndex = new Random().nextInt(getCustomers().size() - 1);
    return getCustomers().get(randomListIndex);
  }

  private static List<Customer> getCustomers() {

    List<Customer> customerList = new ArrayList<>();

    customerList.add(new Customer(1L, "Adam", "Foy"));
    customerList.add(new Customer(2L, "Adam", "Doe"));
    customerList.add(new Customer(3L, "Hugh", "Connor"));
    customerList.add(new Customer(4L, "John", "Foy"));
    customerList.add(new Customer(5L, "Lisa", "Connor"));
    customerList.add(new Customer(6L, "Linda", "Kowalski"));
    customerList.add(new Customer(7L, "Jessica", "Kowalski"));
    customerList.add(new Customer(8L, "Laura", "Thiem"));
    customerList.add(new Customer(9L, "Tom", "Herzog"));
    customerList.add(new Customer(10L, "Greg", "Mitt"));
    customerList.add(new Customer(11L, "William", "Abramovicz"));
    customerList.add(new Customer(12L, "William", "Kerrs"));
    customerList.add(new Customer(13L, "Damian", "Frane"));
    customerList.add(new Customer(14L, "Pat", "Long"));
    customerList.add(new Customer(15L, "Pat", "Foy"));
    customerList.add(new Customer(16L, "Antek", "Frane"));
    customerList.add(new Customer(17L, "Adam", "Thiem"));
    customerList.add(new Customer(18L, "Liam", "Foy"));
    customerList.add(new Customer(19L, "Eugene", "Monday"));
    customerList.add(new Customer(20L, "Austin", "Stanley"));
    customerList.add(new Customer(21L, "Ann", "Frisby"));
    customerList.add(new Customer(22L, "Michael", "Kiepski"));
    customerList.add(new Customer(23L, "Bart", "Finnan"));
    return customerList;
  }

}
