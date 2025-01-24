package kz.applicationweb.usercontrollsystemoop.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
public class JwtUtil {
    private static final String SECRET_KEY = "yourSecretKey";
    private static final long EXPIRATION_TIME = 86400000;

    public static String generateToken(String username, boolean isAdmin) {
        return Jwts.builder()
                .setSubject(username)
                .claim("isAdmin",isAdmin)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean isAdmin(String token) {
        return (boolean) Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("isAdmin");
    }
}
