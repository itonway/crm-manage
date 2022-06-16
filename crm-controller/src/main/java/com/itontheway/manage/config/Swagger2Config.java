package com.itontheway.manage.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@EnableKnife4j
@Slf4j
public class Swagger2Config{
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        log.info("Knife4j-Swagger 接口访问地址：【{}】","http://localhost:8081/crm/doc.html");
        return new ApiInfoBuilder()
                //标题
                .title("在线API文档")
                //描述
                .description("在线接口文档")
                //作者信息
                .contact(new Contact("ONTHEWAY","","admin@163.com"))
                .build();
    }
}
