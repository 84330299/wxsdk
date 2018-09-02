package org.chen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean enableSwagger;

    // Swagger 容器初始化配置
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("org.chen.controller")).paths(PathSelectors.any()).build()
                .enable(enableSwagger);
    }

    // 文档信息描述
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Wechat").description("接口文档")
                .termsOfServiceUrl("").license("Version 1.0").licenseUrl("#")
                .version("1.0").build();
    }

}
