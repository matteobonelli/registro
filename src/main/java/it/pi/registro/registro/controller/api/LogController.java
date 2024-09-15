package it.pi.registro.registro.controller.api;

import it.pi.registro.registro.dto.DashboardResultDTO;
import it.pi.registro.registro.model.PropagateJsonResponse;
import it.pi.registro.registro.service.DashboardService;
import it.pi.registro.registro.service.LogService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/log")
public class LogController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping
    public ResponseEntity<DashboardResultDTO> getStatistics(){
        return new ResponseEntity<>(DashboardResultDTO
                .builder()
                .usersByCity(dashboardService.getUsersByCity())
                .usersByYears(dashboardService.getUsersByYear())
                .usersBySchoolClasses(dashboardService.getUsersBySchoolCLass())
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("/getLog")
    public ResponseEntity<PropagateJsonResponse> getLog(){
        logger.info("GetLog Controller Executed");
        return new ResponseEntity<>(
                logService.getLog(),
                HttpStatus.OK);
    }

}
