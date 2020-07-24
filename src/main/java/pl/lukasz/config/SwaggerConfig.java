package pl.lukasz.config;

import static springfox.documentation.builders.PathSelectors.any;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getApiInfo())
        .genericModelSubstitutes(ResponseEntity.class)
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("pl.lukasz"))
        .paths(any())
        .build();
  }

  private ApiInfo getApiInfo() {
    final Contact contact = new Contact("Lukasz Myrta",
        "",
        "lukmyrta@gmail.com");

    return new ApiInfoBuilder()
        .title("Simple Shop-API")
        .description("Order CRUD operations ")
        .version("1.0.0")
        .license("Apache License 2.0")
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
        .contact(contact)
        .build();
  }
}
