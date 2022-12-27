package br.com.franca.lojasmulti.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "br.com.franca.lojasmulti.controller")
public class SpringFoxConfig {

    private static final String BASE_PACKAGES = "com.franca.moneyalgaapi.resource";
    private static final String ERROR = "String";

    @Value("${info.app.name}")
    private String apiTitle;

    @Value("${info.app.description}")
    private String apiDescription;

    @Value("${info.app.version}")
    private String apiVersion;

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.franca.lojasmulti.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        return new ApiInfoBuilder()
                .title(apiTitle)
                .description(apiDescription)
                .termsOfServiceUrl("aa")
                .license("Apache Licence")
                .licenseUrl("https://www.apache.org/licesen.html")
                .contact(new Contact("Tiago França","https://github.com/tiagofrancarita",
                        "tiagofranca.rita@gmail.com"))
                .build();
    }

}
