package it.pi.registro.registro.configuration;

import it.pi.registro.registro.mapper.UserMapper;
import it.pi.registro.registro.mapper.UserMapperImpl;
import it.pi.registro.registro.mapper.VoteMapper;
import it.pi.registro.registro.mapper.VoteMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
