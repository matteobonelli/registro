package it.pi.registro.registro.service.impl;

import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.entity.ApiKey;
import it.pi.registro.registro.entity.User;
import it.pi.registro.registro.entity.UserDetail;
import it.pi.registro.registro.exception.BadRequestException;
import it.pi.registro.registro.repository.ApiKeyRepository;
import it.pi.registro.registro.repository.UserRepository;
import it.pi.registro.registro.service.ApiKeyService;
import it.pi.registro.registro.service.AttendanceService;
import it.pi.registro.registro.service.ImportService;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ApiKeyServiceImpl implements ApiKeyService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    @Autowired
    ApiKeyRepository apiKeyRepository;


    @Override
    public List<ApiKey> getApiKeys() {
        return apiKeyRepository.findAll();
    }
}
