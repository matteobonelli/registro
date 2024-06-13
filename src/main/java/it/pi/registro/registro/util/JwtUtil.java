package it.pi.registro.registro.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.pi.registro.registro.configuration.RegistroProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    private RegistroProp registroProp;

    public String generateToken(){
        String jwtToken = "";

        return Jwts
                .builder()
                .setSubject(registroProp.getJwtSubject())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + registroProp.getMillis()))
                .signWith(SignatureAlgorithm.HS512, registroProp.getJwtSignature())
                .compact();
    }
}
