package it.pi.registro.registro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsvImportRequest {

    @NotBlank
    private String fileName;
    @NotBlank
    private String base64File;

    @Override
    public String toString() {
        return "CsvImportRequest{" +
                "fileName='" + fileName + '\'' +
                ", base64File='" + base64File + '\'' +
                '}';
    }
}
