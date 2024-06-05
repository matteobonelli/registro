package it.pi.registro.registro.service.impl;

import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.entity.*;
import it.pi.registro.registro.exception.BadRequestException;
import it.pi.registro.registro.repository.UserRepository;
import it.pi.registro.registro.service.AttendanceService;
import it.pi.registro.registro.service.ImportService;
import jakarta.annotation.PostConstruct;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;

@Service
@AllArgsConstructor
@Transactional
public class ImportServiceImpl implements ImportService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    private RequestLog requestLog;

    @Autowired
    UserRepository userRepository;

    @Override
    public void importCsv(CsvImportRequest csvImportRequest) throws Exception{
        logger.info("Base 64 is:");
        logger.info(csvImportRequest.getBase64File());
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(csvImportRequest.getBase64File());
            InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(decodedBytes), StandardCharsets.UTF_8);
            CSVParser csvParser =
                    CSVFormat
                            .DEFAULT
                            .withDelimiter(';')
                            .withFirstRecordAsHeader()
                            .withHeader("FirstName", "LastName", "email", "Password", "Age", "Address", "City", "Type")
                            .parse(reader);

            for (CSVRecord csvRecord : csvParser) {
                userRepository.save(User
                        .builder()
                        .firstName(csvRecord.get("FirstName"))
                        .lastName(csvRecord.get("LastName"))
                        .email(csvRecord.get("email"))
                        .password(csvRecord.get("Password"))
                        .birth_date(LocalDate.now())
                        .userDetail(UserDetail
                                .builder()
                                .address(csvRecord.get("Address"))
                                .city(csvRecord.get("City"))
                                .build())
                        .build());
            }
            reader.close();
            csvParser.close();
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostConstruct
    public void postConstruct(){
        requestLog.setCallDate(LocalDateTime.now());
        System.out.println("postConstruct");
    }
}
