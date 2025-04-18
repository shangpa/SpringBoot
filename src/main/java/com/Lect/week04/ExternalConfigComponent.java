package com.Lect.week04;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:static/external.properties")
@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class ExternalConfigComponent {
    @Value("${server.port}")
    private String serverPort;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${message.greeting}")
    private String greeting;

    @Value("${spring.mvc.view.suffix}")
    private String suffix;

    private String url;
    private String userName;
    private String password;

}
