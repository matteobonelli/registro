package it.pi.registro.registro.configuration;

import it.pi.registro.registro.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Bean
    public HttpInterceptor httpInterceptor(){
        return new HttpInterceptor();
    }

    public @Override void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor());
    }
}
