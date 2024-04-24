package it.pi.registro.registro.configuration;

import it.pi.registro.registro.mapper.UserMapper;
import it.pi.registro.registro.mapper.UserMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }
}
