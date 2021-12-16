package com.bridgelabz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/****************************************************************************************************
 * Purpose: This class is to configure the swagger for the application.
 *
 * @author Ashwith
 * @since 13/12/21
 ****************************************************************************************************/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * This method is used to specify the swagger to which API(Application Programming Interface)
     * to show on Swagger UI(User Interface) console.
     *
     * @return the docket link which has the information about API.
     */
    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Employee Payroll")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bridgelabz.controller"))
                .build();
    }

    /**
     * This method is to provide extra details of the Application to get the proper idea in the Swagger UI.
     *
     * @return the swagger API information
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employee Payroll Application")
                .description("Sample Documentation Generated Using SWAGGER2 for Employee Payroll Application")
                .termsOfServiceUrl("https://github.com/ashwith31")
                .license("License")
                .licenseUrl("https://github.com/ashwith31")
                .version("1.0")
                .build();
    }
}
