package pl.lukasz.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.lukasz.model.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public abstract class IntegrationTestBase {

  protected static final String ORDER_SERVICE_PATH = "/order";
  protected static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_UTF8;

  @Autowired
  protected MockMvc mockMvc;

  @Qualifier("shopObjectMapper")
  @Autowired
  protected ObjectMapper mapper;

  protected Order callRestToGetOrderById(long id) throws Exception {
    String response = mockMvc
        .perform(get(ORDER_SERVICE_PATH + "/" + id))
        .andExpect(content().contentType(JSON_CONTENT_TYPE))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();
    return jsonToOrder(response);
  }

  protected List<Order> callRestToGetAllOrders() throws Exception {
    String response = mockMvc
        .perform(get(ORDER_SERVICE_PATH))
        .andExpect(content().contentType(JSON_CONTENT_TYPE))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();
    return jsonToOrderList(response);
  }

  protected long callRestToAddOrderAndReturnId(Order orderRequest) throws Exception {
    String response = mockMvc
        .perform(post(ORDER_SERVICE_PATH)
            .content(toJson(orderRequest))
            .contentType(JSON_CONTENT_TYPE))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();
    return Long.parseLong(response);
  }

  protected MockHttpServletResponse callRestToAddOrderAndReturnResponse(Order orderRequest) throws Exception {
    return mockMvc
        .perform(post(ORDER_SERVICE_PATH)
            .content(toJson(orderRequest))
            .contentType(JSON_CONTENT_TYPE))
        .andReturn().getResponse();
  }

  protected int callRestToUpdateOrderAndReturnStatus(long id, Order updatedOrder) throws Exception {
    return mockMvc
        .perform(put(ORDER_SERVICE_PATH + "/" + id)
            .content(toJson(updatedOrder))
            .contentType(JSON_CONTENT_TYPE))
        .andReturn().getResponse().getStatus();
  }

  protected int callRestToDeleteOrderByIdAndReturnStatus(long id) throws Exception {
    return mockMvc
        .perform(delete(ORDER_SERVICE_PATH + "/" + id))
        .andReturn().getResponse().getStatus();
  }

  protected List<String> getValidationResultFromResponse(MockHttpServletResponse response) throws Exception {
    return mapper.readValue(response.getContentAsString(), new TypeReference<List<String>>() {
    });
  }

  private Order jsonToOrder(String jsonOrder) throws Exception {
    return mapper.readValue(jsonOrder, Order.class);
  }

  private List<Order> jsonToOrderList(String response) throws IOException {
    return mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Order.class));
  }

  private String toJson(Object object) throws Exception {
    return mapper.writeValueAsString(object);
  }
}
