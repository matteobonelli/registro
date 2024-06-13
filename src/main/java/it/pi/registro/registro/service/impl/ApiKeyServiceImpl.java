package it.pi.registro.registro.service.impl;

import it.pi.registro.registro.configuration.RegistroProp;
import it.pi.registro.registro.configuration.UrlWhiteListProp;
import it.pi.registro.registro.constant.Constants;
import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.entity.ApiKey;
import it.pi.registro.registro.entity.RequestLog;
import it.pi.registro.registro.entity.User;
import it.pi.registro.registro.entity.UserDetail;
import it.pi.registro.registro.exception.ApiValidationException;
import it.pi.registro.registro.exception.BadRequestException;
import it.pi.registro.registro.repository.ApiKeyRepository;
import it.pi.registro.registro.repository.RequestLogRepository;
import it.pi.registro.registro.repository.UserRepository;
import it.pi.registro.registro.service.ApiKeyService;
import it.pi.registro.registro.service.AttendanceService;
import it.pi.registro.registro.service.ImportService;
import it.pi.registro.registro.service.LogService;
import it.pi.registro.registro.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    @Autowired
    ApiKeyRepository apiKeyRepository;

    @Autowired
    UrlWhiteListProp urlWhiteListProp;

    @Autowired
    RequestLogRepository requestLogRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RegistroProp registroProp;

    @Autowired
    private LogService logService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public List<ApiKey> getApiKeys() {
        return apiKeyRepository.findAll();
    }

    @Override
    public Boolean getAndValidateApiKeys(String apiKey, String uri) {
            if (Arrays.stream(urlWhiteListProp.getWhiteList()).toList().contains(uri) &&
                    apiKeyRepository.findValidApiKeys(apiKey).isEmpty()) {
                throw new ApiValidationException(Constants.ERROR_API_NOT_VALID_MESSAGE,
                        Constants.ERROR_API_NOT_VALID_CODE);
            }
            if (Arrays.stream(urlWhiteListProp.getBlackList()).toList().contains(uri)){
                throw new BadRequestException("Ciao");
            }

        return true;
    }

    @Override
    public String getExternalApiData(LocalDateTime requestDate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String credentials = registroProp.getUsername() + ":" + registroProp.getPassword();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        headers.set("Authorization", "Basic " + encodedCredentials);

        String jwtToken = jwtUtil.generateToken();
        String jsonBody = "{\"jwt\": \"" + jwtToken + "\"}";

        // Create the HttpEntity with the headers
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        System.out.println("REQUEST BODY: " + requestEntity.getBody());
        // Make the request to the Node.js server
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                registroProp.getNodeUrl(), // Ensure this is the correct URL
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        String responseBody = responseEntity.getBody();
        int responseStatusCode = responseEntity.getStatusCode().value();

        RequestLog requestLog = new RequestLog(
                registroProp.getNodeUrl(),
                "",
                requestDate,
                LocalDateTime.now(),
                HttpMethod.GET.name(),
                requestEntity.getBody(),
                responseStatusCode,
                responseBody
        );

        requestLogRepository.save(requestLog);
        return responseBody +
                restTemplate.getForObject(registroProp.getPythonUrl(), String.class);
    }
}
