package it.pi.registro.registro.interceptor;

import it.pi.registro.registro.configuration.RegistroProp;
import it.pi.registro.registro.entity.ApiKey;
import it.pi.registro.registro.exception.ApiValidationException;
import it.pi.registro.registro.exception.BadRequestException;
import it.pi.registro.registro.service.ApiKeyService;
import it.pi.registro.registro.service.impl.ApiKeyServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.Objects;

public class HttpInterceptor implements HandlerInterceptor {

    private final ApiKeyServiceImpl apiKeyService;

    public HttpInterceptor(ApiKeyServiceImpl apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // This method is invoked before the controller is called
        // You can add headers here
        String uri = request.getRequestURI();
        String apiKey = request.getHeader("Pippetto");
        response.addHeader("Custom-Header", "Value");
        System.out.println(request.getRemoteHost());
        return apiKeyService.getAndValidateApiKeys(apiKey, uri);
    }
}
