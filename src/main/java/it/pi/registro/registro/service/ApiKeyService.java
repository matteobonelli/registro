package it.pi.registro.registro.service;

import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.entity.ApiKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiKeyService {
    List<ApiKey> getApiKeys();

}
