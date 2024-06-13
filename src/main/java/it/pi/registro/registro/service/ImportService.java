package it.pi.registro.registro.service;

import it.pi.registro.registro.dto.request.CsvImportRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface ImportService {
    void importCsv(CsvImportRequest csvImportRequest, HttpServletRequest requestURI) throws Exception;

}
