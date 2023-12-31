package com.securityTest.loginApplication.config;

import com.securityTest.loginApplication.filter.RandomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<RandomFilter> firstFilter() {
        FilterRegistrationBean<RandomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RandomFilter());
        //registrationBean.addUrlPatterns("/log/*"); // Specify the URL patterns to which this filter should apply
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
