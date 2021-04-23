package pe.blaze.test.expose;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.blaze.test.business.CustomerService;
import pe.blaze.test.model.Customer;

/**
 * <b>Class</b>: CustomerController <br/>
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
@RestController
@RequestMapping("/customer")
@Slf4j
@CrossOrigin
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  /**
   * This method is used to get only one responseModuleEntity.
   * @return one examples.
   * @exception IOException is a generic error.
   */
  @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Listar clientes",
      notes = "Método para listar clientes",
      response = Customer.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = Customer.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public List<Customer> listCustomer() {
    return customerService.listCustomer();
  }

  /**
   * This method is used to get only one responseModuleEntity.
   * @param body This is the first paramter to method.
   * @return one examples.
   * @exception IOException is a generic error.
   */
  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Agregar clientes",
      notes = "Método para agregar clientes",
      response = Customer.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = Customer.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public Customer addCustomer(
      @ApiParam(value = "Customer", required = true)
      @RequestBody Customer body) {
    log.info(body.toString());
    return customerService.addCustomer(body);
  }

  /**
   * This method is used to get only one responseModuleEntity.
   * @param body This is the first paramter to method.
   * @return one examples.
   * @exception IOException is a generic error.
   */
  @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Actualizar clientes",
      notes = "Método para actualizar clientes",
      response = Optional.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = Optional.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public Optional<Customer> updateCustomer(
      @ApiParam(value = "Customer", required = true)
      @RequestBody Customer body) {
    log.info(body.toString());
    return customerService.updateCustomer(body);
  }

  /**
   * This method is used to get only one responseModuleEntity.
   * @param parameter This is the first paramter to method.
   * @return one examples.
   * @exception IOException is a generic error.
   */
  @GetMapping(value = "/delete/{idCustomer}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Eliminar clientes",
      notes = "Método para eliminar clientes",
      response = Boolean.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = Boolean.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public Boolean deleteCustomer(
      @ApiParam(value = "idCustomer", required = true)
      @PathVariable("idCustomer") String parameter) {
    log.info(parameter);
    return customerService.deleteCustomer(parameter);
  }

  /**
   * This method is used to get only one responseModuleEntity.
   * @param parameter This is the first paramter to method.
   * @return one examples.
   * @exception IOException is a generic error.
   */
  @GetMapping(value = "/generate-customer/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Generar clientes",
      notes = "Método para generar clientes",
      response = String.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = String.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public String generateCustomer(
          @ApiParam(value = "amount", required = true)
          @PathVariable("amount") Integer parameter) {
    log.info(String.valueOf(parameter));
    return customerService.generateCustomer(parameter);
  }
}
