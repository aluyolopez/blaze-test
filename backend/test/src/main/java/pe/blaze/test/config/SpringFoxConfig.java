package pe.blaze.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <b>Class</b>: SpringFoxConfig <br/>
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
@Configuration
@EnableSwagger2
@Slf4j
public class SpringFoxConfig {

  @Value("${info.project.expose.package}")
  private String basePackage;

  @Value("${info.project.version}")
  private String description;

  @Value("${info.project.title}")
  private String title;

  @Value("${info.project.description}")
  private String version;

  /**
   * createRestApi.
   *
   * @return
   */
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage(basePackage))
        .build();
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder()
        .description(description)
        .title(title)
        .version(version)
        .build();
  }

}
