package pe.blaze.test.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.blaze.test.model.Customer;
import pe.blaze.test.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepositoryMock;

    @BeforeEach
    void setUp() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(Customer
                .builder()
                .idCustomer("asd123")
                .firstName("Anthony")
                .lastName("Luyo")
                .email("aluyolopez@gmail.com")
                .phoneNumber("987654321")
                .birthDate("1994-01-01")
                .build());
        when(customerRepositoryMock.findAll()).thenReturn(customerList);
    }

    @Test
    void listCustomer() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(Customer
                .builder()
                .idCustomer("asd123")
                .firstName("Anthony")
                .lastName("Luyo")
                .email("aluyolopez@gmail.com")
                .phoneNumber("987654321")
                .birthDate("1994-01-01")
                .build());
        when(customerService.listCustomer())
                .thenReturn(customerList);
    }

    @Test
    void addCustomer() {
        when(customerService.addCustomer(Customer
                .builder()
                .idCustomer("asd123")
                .firstName("Anthony")
                .lastName("Luyo")
                .email("aluyolopez@gmail.com")
                .phoneNumber("987654321")
                .birthDate("1994-01-01")
                .build()))
                .thenReturn(Customer
                        .builder()
                        .idCustomer("asd123")
                        .firstName("Anthony")
                        .lastName("Luyo")
                        .email("aluyolopez@gmail.com")
                        .phoneNumber("987654321")
                        .birthDate("1994-01-01")
                        .build());
    }

    @Test
    void updateCustomer() {
        Optional<Customer> customerOptional = Optional.ofNullable(Customer
                .builder()
                .idCustomer("asd123")
                .firstName("Anthony")
                .lastName("Luyo")
                .email("aluyolopez@gmail.com")
                .phoneNumber("987654321")
                .birthDate("1994-01-01")
                .build());
        when(customerService.updateCustomer(Customer
                .builder()
                .idCustomer("asd123")
                .firstName("Anthony")
                .lastName("Luyo")
                .email("aluyolopez@gmail.com")
                .phoneNumber("987654321")
                .birthDate("1994-01-01")
                .build()))
                .thenReturn(customerOptional);
    }

    @Test
    void deleteCustomer() {
        when(customerService.deleteCustomer("asd123"))
                .thenReturn(Boolean.TRUE);
    }

    @Test
    void generateCustomer() {
        when(customerService.generateCustomer(2))
                .thenReturn("Base de datos poblada con 2 registros.");
    }
}