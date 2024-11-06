package org.example.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.auth.interceptor.IncomingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Jitendra (Jeetu)
 */

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IncomingInterceptor());
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(mapper));
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }
}
