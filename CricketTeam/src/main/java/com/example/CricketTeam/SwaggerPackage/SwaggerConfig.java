package com.example.CricketTeam.SwaggerPackage;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Welcome to Cricket database",
                description = "This is Cricket API",
                summary = "This Api contains Cricket information",
                termsOfService = "Terms&Conditions applied",
                contact = @Contact(name = "Channanagouda",
                        email = "achannanagouda4444@gmail.com"),
                license = @License(name = "Channanagouda"),
                version ="API/V4"),
        security = @SecurityRequirement(name = "JWTSecurity")
)
@SecurityScheme(
        name = "JWTSecurity",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SwaggerConfig {
}
