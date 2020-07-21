package pl.lukasz.model;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Buyer {

//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @ApiModelProperty(value = "The id of buyer.", dataType = "Long", position = -1)
//  private Long id;

  @ApiModelProperty(value = "The name of buyer.", example = "John", dataType = "String")
  private String name;

  @ApiModelProperty(value = "The surname of buyer.", example = "Doe", dataType = "String", position = 1)
  private String surname;

}
