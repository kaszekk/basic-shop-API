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
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "Order id.", dataType = "Long", position = -1)
  private Long id;

  @ApiModelProperty(value = "Short order description.", example = "Pizza", dataType = "String")
  private String description;

  @Embedded
  private Buyer buyer;

  @ApiModelProperty(value = "Order date.", example = "2020-07-21", dataType = "LocalDate")
  private LocalDate date;

  public Order(String description, Buyer buyer, LocalDate date) {
    this.description = description;
    this.buyer = buyer;
    this.date = date;
  }
}
