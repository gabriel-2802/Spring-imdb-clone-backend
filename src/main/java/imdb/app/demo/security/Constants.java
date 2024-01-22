package imdb.app.demo.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Constants {
    public static final long JWT_EXPIRATION = 70000;
    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
