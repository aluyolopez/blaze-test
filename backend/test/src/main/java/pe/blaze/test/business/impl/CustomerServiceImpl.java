package pe.blaze.test.business.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.blaze.test.business.CustomerService;
import pe.blaze.test.model.Customer;
import pe.blaze.test.repository.CustomerRepository;

/**
 * <b>Class</b>: CustomerServiceImpl <br/>
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
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> listCustomer() {
        log.info("Listado:");
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer body) {
        log.info("Agregar: " + body);
        try {
            customerRepository.insert(body);
        } catch(Exception e) {
            log.error(String.valueOf(e));
        }
        return body;
    }

    @Override
    public Optional<Customer> updateCustomer(Customer body) {
        log.info("Actualizar: " + body);
        Optional<Customer> optionalCustomer = customerRepository.findById(body.getIdCustomer());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(body.getFirstName());
            customer.setLastName(body.getLastName());
            customer.setEmail(body.getEmail());
            customer.setPhoneNumber(body.getPhoneNumber());
            customer.setBirthDate(body.getBirthDate());
            customerRepository.save(customer);
        }
        return optionalCustomer;
    }

    @Override
    public Boolean deleteCustomer(String parameter) {
        Boolean result = customerRepository.existsById(parameter);
        if (Boolean.TRUE.equals(result)) {
            log.info("Eliminar: " + parameter);
            customerRepository.deleteById(parameter);
        }
        return result;
    }

    @Override
    public String generateCustomer(Integer parameter) {
        for (int i = 0 ; i < parameter ; i++) {
            addCustomer(Customer
                .builder()
                .idCustomer("c" + i)
                .firstName("Nombre " + i)
                .lastName("Apellido " + i)
                .email("email" + i + "@gmail.com")
                .phoneNumber("9" + generateRandomNumber(11111111, 99999999))
                .birthDate(generateRandomNumber(1, 30)
                        + "-"
                        + generateRandomNumber(1, 12)
                        + "-"
                        + generateRandomNumber(1980, 2000))
                .build());
        }
        return "Base de datos poblada con " + parameter + " registros.";
    }

    private String generateRandomNumber(Integer from, Integer to) {
        return new Random().nextInt(to - from + 1) + from + "";
    }
}
