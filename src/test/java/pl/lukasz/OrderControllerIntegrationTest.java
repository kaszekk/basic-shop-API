package pl.lukasz;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.lukasz.model.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderControllerIntegrationTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper mapper;

  protected static final String ORDER_SERVICE_PATH = "/order";

  protected static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_UTF8;

  @Test
  public void shouldReturnIdWhenEmployeeAddedSuccessfully() throws Exception {
    //Given
    LocalDate orderDate = LocalDate.of(2020, 7, 20);
    Order orderRequest = new Order(1L,"Pizza", "Franek", "Kimono", orderDate);
    long expectedId = 1;

    //When
    long actual = callRestToAddOrderAndReturnId(orderRequest);

    //Then
    assertEquals(expectedId, actual);
  }

  private long callRestToAddOrderAndReturnId(Order orderRequest) throws Exception {
    String response = mockMvc
        .perform(post(ORDER_SERVICE_PATH)
            .content(json(orderRequest))
            .contentType(JSON_CONTENT_TYPE))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();
    return Long.parseLong(response);
  }

  private String json(Object object) throws Exception {
    return mapper.writeValueAsString(object);
  }

//  @Test
//  public void shouldAddEmployee() throws Exception {
//    //Given
//    Employee expectedEmployee = new Employee("John", "Doe", 2, 80000);
//    expectedEmployee.setId(1);
//
//    //When
//    callRestToAddEmployeeAndReturnId(expectedEmployee);
//    Employee addedEmployee = callRestToGetEmployeeById(1);
//    int numberOfAllEmployeesInDb = callRestToGetAllEmployees().size();
//
//    //Then
//    assertEquals(1, numberOfAllEmployeesInDb);
//    assertEquals(expectedEmployee, addedEmployee);
//  }

}
