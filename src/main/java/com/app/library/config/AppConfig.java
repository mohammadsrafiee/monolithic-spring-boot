package com.app.library.config;

import com.app.library.common.filter.UniqueIdPerHttpRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<UniqueIdPerHttpRequestFilter> myFilter() {
        FilterRegistrationBean<UniqueIdPerHttpRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UniqueIdPerHttpRequestFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
