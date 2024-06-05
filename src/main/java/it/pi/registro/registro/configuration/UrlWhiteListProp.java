package it.pi.registro.registro.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@PropertySource("classpath:urlWhitelist.properties")
public class UrlWhiteListProp {

    @Value("${registro.urls.whiteList}")
    private String[] whiteList;

    @Value("${registro.urls.blackList}")
    private String[] blackList;
}
