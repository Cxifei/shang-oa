package com.rimi.consultant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 配置文件
 *
 * @author szf
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        String basePackage = "com.rimi.consultant.controller";
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).select().apis(RequestHandlerSelectors.basePackage(basePackage)).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        String createName = "shangzf";
        String serviceName = "咨询模块（学生）";
        String createUrl = "http://www.rimionline.com";
        String createEmail = "shangzf@rimionline.com";
        String description = "咨询模块";
        return (new ApiInfoBuilder()).title(serviceName + " Restful APIs").description(description).contact(new Contact(createName, createUrl, createEmail)).version("1.0").build();
    }
}
