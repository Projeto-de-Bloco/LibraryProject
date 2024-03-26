package br.edu.infnet.BibliotecaInfnet.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(

        info = @Info(

                contact = @Contact(name = "INFNET Library", email = "library.contato@al.infnet.edu.br"),
                description = "Book library for INFNET members ",
                title = "INFNET Library"),

        servers = {@Server(description = "Local ENV", url = "http://localhost:8080")}

)


public class OpenApiConfig {

}