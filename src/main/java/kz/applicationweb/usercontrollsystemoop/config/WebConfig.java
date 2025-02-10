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
        Map<String, String> htmlRoutes = new java.util.HashMap<>();
        htmlRoutes.put("", "index.html");
        htmlRoutes.put("/", "index.html");
        htmlRoutes.put("/registration", "registration.html");
        htmlRoutes.put("/login", "login.html");
        htmlRoutes.put("/statuses", "statuses.html");
        htmlRoutes.put("/tasks", "tasks.html");
        htmlRoutes.put("/add-task", "add-task.html");
        htmlRoutes.put("/edit-task", "edit-task.html");
        htmlRoutes.put("/profile", "profile.html");
        htmlRoutes.put("/edit-profile", "edit-profile.html");
        htmlRoutes.put("/employees", "employees.html");
        htmlRoutes.put("/students", "students.html");
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
