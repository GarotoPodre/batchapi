package br.com.batch.api.batchapi.infra.security;

import br.com.batch.api.batchapi.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String geradorToken(Usuario usuario){
        //System.out.println(secret);
        try{
            var algoritmo= Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API batch")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .withClaim("role", usuario.getRole())//teste
                    .sign(algoritmo);
        }catch (JWTCreationException exception){
            throw new RuntimeException("erro ao carregar o token jwt", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    //Validando o token
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API batch").build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    public String getRole(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API batch").build()
                    .verify(tokenJWT)
                    .getClaim("role").asString();// get the role
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

}
