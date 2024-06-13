package it.pi.registro.registro.entity;

import it.pi.registro.registro.dto.request.CsvImportRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String callerIp;
    private LocalDateTime requestDate;
    private LocalDateTime responseDate;
    private String httpRequestMethod;
    @Column(length = 10000)
    private String httpRequestBody;
    private int httpResponseCode;
    private String httpResponseMessage;

    public RequestLog(String url,
                      String callerIp,
                      LocalDateTime requestDate,
                      LocalDateTime responseDate,
                      String httpRequestMethod,
                      String httpRequestBody,
                      int httpResponseCode,
                      String httpResponseMessage) {
        this.url = url;
        this.callerIp = callerIp;
        this.requestDate = requestDate;
        this.responseDate = responseDate;
        this.httpRequestMethod = httpRequestMethod;
        this.httpRequestBody = httpRequestBody;
        this.httpResponseCode = httpResponseCode;
        this.httpResponseMessage = httpResponseMessage;
    }

    @Override
    public String toString() {
        return "RequestLog{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", callerIp='" + callerIp + '\'' +
                ", requestDate=" + requestDate +
                ", responseDate=" + responseDate +
                ", httpRequestMethod='" + httpRequestMethod + '\'' +
                ", httpRequestBody='" + httpRequestBody + '\'' +
                ", httpResponseCode=" + httpResponseCode +
                ", httpResponseMessage='" + httpResponseMessage + '\'' +
                '}';
    }
}
