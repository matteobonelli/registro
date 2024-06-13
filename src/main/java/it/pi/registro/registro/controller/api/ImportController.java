package it.pi.registro.registro.controller.api;

import it.pi.registro.registro.dto.request.AttendanceRegisterRequestDTO;
import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.entity.Attendance;
import it.pi.registro.registro.entity.RequestLog;
import it.pi.registro.registro.service.ApiKeyService;
import it.pi.registro.registro.service.AttendanceService;
import it.pi.registro.registro.service.ImportService;
import it.pi.registro.registro.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("import")
public class ImportController {

    @Autowired
    private ImportService importService;

    @Autowired
    private LogService logService;

    @Autowired
    private ApiKeyService apiKeyService;

    @PostMapping("/csv")
    public ResponseEntity<?> importCsv(@Valid @RequestBody CsvImportRequest csvImportRequest,
                                       HttpServletRequest request) throws Exception {
        importService.importCsv(csvImportRequest, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
