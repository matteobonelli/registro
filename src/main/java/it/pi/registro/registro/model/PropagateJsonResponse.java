package it.pi.registro.registro.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
public class PropagateJsonResponse {

        private String pippo;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Map<String, String> receivedData;

    @Override
    public String toString() {
        return "PropagateJsonResponse{" +
                "pippo='" + pippo + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", receivedData=" + receivedData +
                '}';
    }
}
