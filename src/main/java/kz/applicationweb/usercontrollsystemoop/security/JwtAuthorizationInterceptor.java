package kz.applicationweb.usercontrollsystemoop.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.applicationweb.usercontrollsystemoop.util.JwtUtil;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
public class JwtAuthorizationInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public JwtAuthorizationInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(
        @SuppressWarnings("null") HttpServletRequest request, 
        @SuppressWarnings("null") HttpServletResponse response, 
        @SuppressWarnings("null") Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequireRole requireRole = handlerMethod.getMethodAnnotation(RequireRole.class);

        if (requireRole == null) {
            return true;
        }

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid token");
            return false;
        }

        token = token.substring(7);
        String role = jwtUtil.extractRole(token);

        if (!Arrays.asList(requireRole.value()).contains(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Insufficient privileges");
            return false;
        }

        return true;
    }
}
