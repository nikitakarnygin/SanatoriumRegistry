package com.project.sanatoriumregistry.SanatoriumRegistry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SanatoriumRegistryConfigContext {

    public final AppConfiguration env;

    public SanatoriumRegistryConfigContext(AppConfiguration env) {
        this.env = env;
    }

    @Bean
    @Scope("singleton")
    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection(env.URL, env.USER, env.PASSWORD);
    }
}
