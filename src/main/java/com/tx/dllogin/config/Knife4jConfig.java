package com.tx.dllogin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration

//springboot版本过低 不能上高版本 2.09~~~
@EnableSwagger2
@EnableKnife4j
//设置 配置环境不是application-prod.properties时候才生效
@Profile("!prod")
public class Knife4jConfig {


    @Autowired
    private Environment environment;

    @Bean
    public Docket createRestApi() {

        //设置只在开发中环境中启动swagger
        Profiles profiles=Profiles.of("dev");

        //表示如果现在是dev环境，则返回true 开启swagger
        boolean flag=environment.acceptsProfiles(profiles);

        return  new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                //方式一: 配置扫描 所有想在swagger界面的统一管理接口。都必须在此包下
                .apis(RequestHandlerSelectors.basePackage("com.tx.dllogin.controller"))
                //方式二: 只有当方法上有  @ApiOperation 注解时才能生成对应的接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("dd后台Api")
                .description("API")
                .termsOfServiceUrl("dev环境下的-http://localhost:9090/")
                .version("1.0")
                .build();
    }



}