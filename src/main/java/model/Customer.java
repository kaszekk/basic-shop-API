package model;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "The id of customer.", dataType = "Long", position = -1)
  private Long id;

  @ApiModelProperty(value = "The name of customer.", example = "John", dataType = "String")
  private String name;

  @ApiModelProperty(value = "The surname of customer.", example = "Doe", dataType = "String", position = 1)
  private String surname;

}
