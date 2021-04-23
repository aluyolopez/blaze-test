package pe.blaze.test.business;

import java.util.List;
import java.util.Optional;
import pe.blaze.test.model.Customer;

/**
 * <b>Class</b>: CustomerService <br/>
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
public interface CustomerService {
    List<Customer> listCustomer();

    Customer addCustomer(Customer body);

    Optional<Customer> updateCustomer(Customer body);

    Boolean deleteCustomer(String parameter);

    String generateCustomer(Integer parameter);
}
