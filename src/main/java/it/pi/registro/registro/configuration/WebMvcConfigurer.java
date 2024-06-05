package it.pi.registro.registro.configuration;

import it.pi.registro.registro.interceptor.HttpInterceptor;
import it.pi.registro.registro.service.impl.ApiKeyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Autowired
    private ApiKeyServiceImpl apiKeyService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor(apiKeyService));
    }
}
