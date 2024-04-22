package it.pi.registro.registro.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class HttpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // This method is invoked before the controller is called
        // You can add headers here
        response.addHeader("Custom-Header", "Value");
        return true;
    }
}
