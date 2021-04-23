package pe.blaze.test.expose;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pe.blaze.test.TestApplication;
import pe.blaze.test.model.Customer;
import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class)
class CustomerControllerTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void listCustomer() throws Exception {
        String uri = "/customer/list";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        System.out.println("resultado: " + mvcResult);
        Integer status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<Customer> customerList = mapFromJson(content, List.class);
        assertTrue(customerList.size() > 0);
    }

    @Test
    void addCustomer() throws Exception {
        String uri = "/customer/add";
        Customer customer = new Customer();
        customer.setIdCustomer("1");
        customer.setFirstName("Nombre");
        customer.setLastName("apellido");
        customer.setEmail("nombre@gmail.com");
        customer.setPhoneNumber("987654321");
        customer.setBirthDate("01-05-2000");

        assertThat(customer.getIdCustomer()).isNotNull();
        assertThat(customer.getFirstName()).isNotNull();
        assertThat(customer.getLastName()).isNotNull();
        assertThat(customer.getEmail()).isNotNull();
        assertThat(customer.getPhoneNumber()).isNotNull();
        assertThat(customer.getBirthDate()).isNotNull();

        String response = "{\"IdCustomer\":\"1\",\"FirstName\":\"Nombre\",\"LastName\":\"apellido\"," +
                "\"Email\":\"nombre@gmail.com\",\"PhoneNumber\":\"987654321\",\"BirthDate\":\"01-05-2000\"}";
        String inputJson = mapToJson(customer);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        System.out.println("resultado: " + mvcResult);
        Integer status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, response);
    }
}