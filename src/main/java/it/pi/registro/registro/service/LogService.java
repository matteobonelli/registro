package it.pi.registro.registro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.entity.RequestLog;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface LogService {
    RequestLog saveRequest(Object objectRequest,
                           HttpServletRequest request,
                           int response,
                           String responseMessage,
                           LocalDateTime requestTime,
                           LocalDateTime responseTime) throws JsonProcessingException;

    Object getLog();
}
