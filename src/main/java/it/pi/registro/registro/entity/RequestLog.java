package it.pi.registro.registro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "request:log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userIp;
    @Column
    private String apiUrl;
    @Column
    private LocalDateTime callDate;
    @Column
    private String errorMessage;
    @Column
    private int errorCode;
    @Column
    private String base64;
    @Column
    private LocalDateTime responseDate;

}
