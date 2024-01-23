package imdb.app.demo.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Constants {
    public static final long JWT_EXPIRATION = 604800000L; // 7 days
    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final int ADMIN_KEY = 2802;
}
