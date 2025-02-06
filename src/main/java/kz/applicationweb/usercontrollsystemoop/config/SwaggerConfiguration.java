package kz.applicationweb.usercontrollsystemoop.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "Authorization",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfiguration {
    
    @Bean
    public OpenAPI userManagementOpenAPI() {
        AppConfig config = AppConfig.getInstance();
        return new OpenAPI()
            .info(new Info()
                .title(config.getConfig("swagger.title"))
                .description(config.getConfig("swagger.description"))
                .version(config.getConfig("swagger.version"))
            );
    }
}
