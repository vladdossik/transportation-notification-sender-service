package transportation.transportationnotificationsenderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(metaData())
            .select()
            .apis(RequestHandlerSelectors.basePackage("transportation.notification-sender-service.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
            .title("Notification sender service")
            .description("REST API for user service")
            .version("1.0.0")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
            .extensions(new ArrayList<>())
            .build();
    }
}
