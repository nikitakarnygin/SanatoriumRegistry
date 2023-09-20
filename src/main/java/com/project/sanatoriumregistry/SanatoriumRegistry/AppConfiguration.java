package com.project.sanatoriumregistry.SanatoriumRegistry;

import org.springframework.beans.factory.annotation.Value;

public class AppConfiguration {

    @Value("${spring.datasource.url}")
    public String URL;
    @Value("${spring.datasource.username}")
    public String USER;
    @Value("${spring.datasource.password}")
    public String PASSWORD;
}