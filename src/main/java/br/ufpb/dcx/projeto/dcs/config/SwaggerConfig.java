package br.ufpb.dcx.projeto.dcs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Me Ajude API - Carlos e Victor")
                .version("v1.0.0")
                .description("Use a documentação como referência para consumir a API");

        return new OpenAPI().info(info);
    }
}
