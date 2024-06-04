package it.pi.registro.registro.interceptor;

import it.pi.registro.registro.configuration.RegistroProp;
import it.pi.registro.registro.entity.ApiKey;
import it.pi.registro.registro.service.ApiKeyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.Objects;

public class HttpInterceptor implements HandlerInterceptor {

    @Autowired
    ApiKeyService apiKeyService;

    @Autowired
    RegistroProp registroProp;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // This method is invoked before the controller is called
        // You can add headers here
        response.addHeader("Custom-Header", "Value");
        System.out.println(registroProp.getApiUrls());
//        List<ApiKey> apiKey = apiKeyService.getApiKeys();
//        String apiKeyHeader = request.getHeader("Pippetto");
//        for(ApiKey apiKey1 : apiKey){
//            if(Objects.equals(apiKey1.getApiKey(), apiKeyHeader)){
//                return true;
//            }
//        }
        return true;
    }
}
