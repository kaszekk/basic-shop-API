package pl.lukasz.model;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pl.lukasz.utils.ArgumentValidator;

@Data
@Builder(builderClassName = "NullParameterValidatingBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "Order id.", dataType = "Long", position = -1)
  @With
  private long id;

  @ApiModelProperty(value = "Short order description.", example = "Pizza", dataType = "String")
  private String description;

  @Embedded
  private Buyer buyer;

  @ApiModelProperty(value = "Order date.", example = "2020-07-21", dataType = "LocalDate")
  private LocalDate date;

  public static class NullParameterValidatingBuilder {

    public Order build() {
      return new Order(description, buyer, date);
    }
  }

  public Order(String description, Buyer buyer, LocalDate date) {
    validateBuyer(buyer);
    validateOrderDescriptionAndDate(description, date);
    this.description = description;
    this.buyer = buyer;
    this.date = date;
  }

  private void validateBuyer(Buyer buyer) {
    ArgumentValidator.ensureNotNull(buyer, "buyer");
    ArgumentValidator.ensureNotNull(buyer.getName(), "buyer name");
    ArgumentValidator.ensureNotNull(buyer.getSurname(), "buyer surname");
  }

  private void validateOrderDescriptionAndDate(String description, LocalDate date) {
    ArgumentValidator.ensureNotNull(description, "order description");
    ArgumentValidator.ensureNotNull(date, "order date");
  }

}
