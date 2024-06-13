package it.pi.registro.registro.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RegistroProp {

    @Value("${registro.vote.month.start}")
    private int voteStartDate;

    @Value("${registro.vote.month.end}")
    private int voteEndDate;

    @Value("${registro.language.default}")
    private String defaultLang;

    @Value("${registro.api.urls}")
    private String apiUrls;

    @Value("${registro.api.propagateJWTPython}")
    private String pythonUrl;

    @Value("${registro.api.propagateJWTNode}")
    private String nodeUrl;

    @Value("${registro.api.millis}")
    private int millis;

    @Value("${registro.api.jwtSubject}")
    private String jwtSubject;

    @Value("${registro.api.jwtSignature}")
    private String jwtSignature;

    @Value("${registro.api.usernamez}")
    private String username;

    @Value("${registro.api.password}")
    private String password;

    @Value("${registro.api.getLog}")
    private String getLogUrl;

    @Value("${registro.api.saveLog}")
    private String getSaveLogUrl;

}
