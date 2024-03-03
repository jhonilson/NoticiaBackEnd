package com.jmasco.noticias.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${jmasco.openapi.dev-url}")
    private String devUrl;

    @Value("${jmasco.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("jhonilson@gmail.com");
        contact.setName("John Masco");
        contact.setUrl("https://www.xxxxxx.com");

        License mitLicense = new License().name("Sin License").url("");

        Info info = new Info()
                .title("CRUD Noticias API")
                .version("1.0")
                .contact(contact)
                .description("API que expone endpoints para administrar noticias.").termsOfService("sin terminos de servicio")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
