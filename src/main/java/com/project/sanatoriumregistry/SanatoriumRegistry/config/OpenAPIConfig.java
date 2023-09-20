package com.project.sanatoriumregistry.SanatoriumRegistry.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI libraryProject() {
        return new OpenAPI()
                .info(new Info()
                        .title("Санаторий \"Источник\". Регистратура")
                        .description("Сервис, позволяющий просматривать информацию о посетителях" +
                                " и записывать их на услуги")
                        .version("0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Nikita Karnygin").email("nikita.karnygin2505@yandex.ru").url(""))
                );
    }

}