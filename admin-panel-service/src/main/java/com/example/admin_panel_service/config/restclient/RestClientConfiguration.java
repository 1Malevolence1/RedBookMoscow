package com.example.admin_panel_service.config.restclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    // @TODO добавить путь localhost в yaml файл
    @Value("${name.server.localhost}")
    private String localhostDataBase;

    @Bean
    public RestClient restClientDataBase(){
        return RestClient.create(localhostDataBase);
    }
}
