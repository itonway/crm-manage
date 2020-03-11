package com.itontheway.manage.config;

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
 * @author: 公众号 itontheway
 * @description: swagger 配置
 * @date 2020/3/10 20:19
 */
@Configuration
@EnableSwagger2
public class Swagger2Config{
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itontheway.manage"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("SpringBoot利用Swagger构建API文档")
                //描述
                .description("itontheway微信公众号")
                //作者信息
                .contact(new Contact("itontheway","https://github.com/itonway/crm-manage","itontheway@163.com"))
                .build();
    }

}
