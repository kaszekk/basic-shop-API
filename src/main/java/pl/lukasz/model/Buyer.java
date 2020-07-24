package pl.lukasz.model;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Buyer {

  @ApiModelProperty(value = "Buyer's name", example = "John", dataType = "String")
  private String name;

  @ApiModelProperty(value = "Buyer's surname", example = "Kowalski", dataType = "String")
  private String surname;
}
