package model;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "model.Order id.", dataType = "Long", position = -1)
  private Long id;

  @ApiModelProperty(value = "Short order description.", example = "Pizza", dataType = "String")
  private String description;

  @ManyToOne(cascade = CascadeType.ALL)
  @NonNull
  private Customer customer;

  @ApiModelProperty(value = "date of order.", example = "21-07-2020", dataType = "LocalDate")
  private LocalDate date;

}
