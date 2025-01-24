package kz.applicationweb.usercontrollsystemoop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@SuppressWarnings("removal")
@Configuration
public class SecurityConfig {
    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
   @SuppressWarnings("removal")
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       HttpSecurity httpSecurity = http.csrf().disable()
               .authorizeHttpRequests()
               .requestMatchers("/login").permitAll()
               .anyRequest().authenticated()
               .and()
               .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
   }
}

