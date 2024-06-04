package it.pi.registro.registro.controller.api;

import it.pi.registro.registro.dto.request.AttendanceRegisterRequestDTO;
import it.pi.registro.registro.dto.request.CsvImportRequest;
import it.pi.registro.registro.entity.Attendance;
import it.pi.registro.registro.service.AttendanceService;
import it.pi.registro.registro.service.ImportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("import")
public class ImportController {

    private ImportService importService;

    @PostMapping("/csv")
    public ResponseEntity<?> importCsv(@Valid @RequestBody CsvImportRequest csvImportRequest) throws Exception {
        importService.importCsv(csvImportRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
