package kz.applicationweb.usercontrollsystemoop.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import kz.applicationweb.usercontrollsystemoop.security.JwtAuthorizationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JwtAuthorizationInterceptor jwtAuthorizationInterceptor;
    
    public WebConfig(JwtAuthorizationInterceptor jwtAuthorizationInterceptor) {
        this.jwtAuthorizationInterceptor = jwtAuthorizationInterceptor;
    }
    
    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthorizationInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/auth/**");
    }

    @Override
    public void addViewControllers(@SuppressWarnings("null") ViewControllerRegistry registry) {
        Map<String, String> htmlRoutes = Map.of(
            "", "index.html",
            "/", "index.html",
            "/registration", "registration.html",
            "/login", "login.html",
            "/statuses", "statuses.html",
            "/tasks", "tasks.html",
            "/add-task", "add-task.html",
            "/edit-task", "edit-task.html"
        );
        for (Map.Entry<String, String> entry : htmlRoutes.entrySet()) {
            registry.addViewController(entry.getKey()).setViewName(entry.getValue());
        }
    }

    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
