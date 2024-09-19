package com.example.user_service.config.restclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {


    @Value("${name.server.localhost}")
    public String localhost;

    @Bean
    public RestClient restClient(){
        return RestClient.create(localhost);
    }
}
