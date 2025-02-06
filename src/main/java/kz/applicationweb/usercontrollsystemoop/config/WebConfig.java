package kz.applicationweb.usercontrollsystemoop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}
