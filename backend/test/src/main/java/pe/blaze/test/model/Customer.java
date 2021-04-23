package pe.blaze.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <b>Class</b>: Customer <br/>
 * <b>Copyright</b>: 2021 Blaze - Anthony Luyo <br/>.
 *
 * @author 2021 Blaze - Anthony Luyo <br/>
 * <u>Service Provider</u>: Blaze <br/>
 * <u>Developed by</u>: Anthony Luyo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     April 22, 2021 Creación de Clase.
 *   </li>
 * </ul>
 */
@ApiModel(description = "Customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "Customer")
public class Customer {
  private static final long serialVersionUID = 1L;

  @JsonProperty("IdCustomer")
  @ApiModelProperty(value = "IdCustomer", example = "122")
  @Id
  private String idCustomer;

  @JsonProperty("FirstName")
  @ApiModelProperty(value = "FirstName", example = "Pedro")
  private String firstName;

  @JsonProperty("LastName")
  @ApiModelProperty(value = "LastName", example = "Suarez")
  private String lastName;

  @JsonProperty("Email")
  @ApiModelProperty(value = "Email", example = "pedro.suarez@gmail.com")
  private String email;

  @JsonProperty("PhoneNumber")
  @ApiModelProperty(value = "PhoneNumber", example = "987654321")
  private String phoneNumber;

  @JsonProperty("BirthDate")
  @ApiModelProperty(value = "BirthDate", example = "01-01-1990")
  private String birthDate;
}
