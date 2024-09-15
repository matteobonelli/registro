package it.pi.registro.registro.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.pi.registro.registro.configuration.RegistroProp;
import it.pi.registro.registro.controller.api.LogController;
import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.entity.RequestLog;
import it.pi.registro.registro.entity.User;
import it.pi.registro.registro.model.PropagateJsonResponse;
import it.pi.registro.registro.repository.RequestLogRepository;
import it.pi.registro.registro.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RegistroProp registroProp;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Override
    public RequestLog saveRequest(Object objectRequest,
                                  HttpServletRequest request,
                                  int response,
                                  String responseMessage,
                                  LocalDateTime requestTime,
                                  LocalDateTime responseTime) throws JsonProcessingException {
        RequestLog requestLog = new RequestLog(
                request.getRequestURI(),
                "",
                requestTime,
                responseTime,
                request.getMethod(),
                objectMapper.writeValueAsString(objectRequest),
                response,
                responseMessage
        );
        System.out.println("\n\n\n\n" + requestLogRepository.save(requestLog) + "\n\n\n\n" );
        return requestLogRepository.save(requestLog);
    }

    @Override
    public PropagateJsonResponse getLog() {
        try{
            logger.info("GetLogService Started");
            LocalDateTime startDate = LocalDateTime.now();
            String log  = restTemplate.getForObject(registroProp.getGetLogUrl(), String.class);
            System.out.println(log);
            PropagateJsonResponse mappedString = convertStringToJson(log);
            mappedString.setStartDate(startDate);
            mappedString.setEndDate(LocalDateTime.now());

            System.out.println(mappedString);
            restTemplate.postForObject(registroProp.getGetSaveLogUrl(), mappedString, String.class);
            logger.info("GetLogService Executed Correctly");
            return mappedString;
        } catch(Exception e){
            logger.error("GetLogService Couldn't be Executed Correctly");
            return null;
        }

    }

    public PropagateJsonResponse convertStringToJson(String logString) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert the string to a JSON object (Map<String, Object>)
            PropagateJsonResponse jsonMap = objectMapper.readValue(logString, PropagateJsonResponse.class);

            // Now you have a JSON object (Map<String, Object>) in Java
            System.out.println(jsonMap);
            return jsonMap;
        } catch (Exception e) {
            // Handle JSON parsing exception
            e.printStackTrace();
            return null;
        }
    }


}
