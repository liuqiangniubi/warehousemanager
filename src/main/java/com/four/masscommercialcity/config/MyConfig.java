package com.four.masscommercialcity.config;

import com.four.masscommercialcity.componet.MyInterceptor;
import com.four.masscommercialcity.componet.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 扩展mvc配置
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //添加路径访问
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/work").setViewName("login/workLogin");
                registry.addViewController("/user/selectProduct").setViewName("delivery");
//                registry.addViewController("/car").setViewName("/car/car");
//                registry.addViewController("/user/end").setViewName("/commons/end");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //添加页面拦截
                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/", "/index.html", "/user/**", "/work/login"
                                , "/work", "/css/**", "/images/**", "/js/**", "/webjars/**", "/font/**", "/path/**"
                                , "/car/**", "/address/**");
                registry.addInterceptor(new UserInterceptor()).addPathPatterns("/car/**", "/address/**");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //配置虚拟路径
                registry.addResourceHandler("/path/**").addResourceLocations("file:E:/img/");

            }
        };
        return webMvcConfigurer;
    }
}
