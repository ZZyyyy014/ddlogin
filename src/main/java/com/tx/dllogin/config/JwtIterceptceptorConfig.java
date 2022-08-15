package com.tx.dllogin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtIterceptceptorConfig implements WebMvcConfigurer {


    @Override //重写拦截器 拦截的是方法执行前  拦截方法
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtIterceptceptor())
                //登录 放行token  不拦截方法
                .addPathPatterns("/**")
                .excludePathPatterns("/user/ddUrl")
                .excludePathPatterns("/user/ddlogin")
                .excludePathPatterns("/user/captcha.jpg");
                //拦截所有路径
        //添加入webmvc 配置里
        WebMvcConfigurer.super.addInterceptors(registry);
    }


    @Override //跨域选择 失效 配置拦截器就失效了 应为是在拦截器后面
    public void addCorsMappings(CorsRegistry registry) {
            //设置 允许的跨域路由
        registry.addMapping("/**")
                //允许跨域的域名
                .allowedOrigins("*")
                 //是否允许 cookies
                .allowCredentials(true)
                //允许方法
                .allowedMethods("*")
                //允许跨域时间
                .maxAge(3600);
    }



}
