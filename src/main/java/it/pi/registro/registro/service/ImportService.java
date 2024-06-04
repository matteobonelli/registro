package it.pi.registro.registro.service;

import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.dto.request.UserRegisterRequest;
import it.pi.registro.registro.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ImportService {
    void importCsv(CsvImportRequest csvImportRequest) throws Exception;

}
