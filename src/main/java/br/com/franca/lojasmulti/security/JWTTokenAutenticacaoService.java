package br.com.franca.lojasmulti.security;


import br.com.franca.lojasmulti.ApplicationContextLoad;
import br.com.franca.lojasmulti.model.Usuario;
import br.com.franca.lojasmulti.repository.UsuarioRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
@Component
public class JWTTokenAutenticacaoService {

    // Tempo de validade 30 min
    //Chave para concatenar com o JWT --> API-GERENCIAMENTO-FINANCAS-2022
    //MD5 Hash	7a470cdb35fb87bf12340e8b6c67f5d3
    //SHA1 Hash	c8c584af49a01643b2ad7f0959933afcb5e2e118
    private static final long EXPIRATION_TIME =	1800000;
    private static final String SECRET = "c8c584af49a01643b2ad7f0959933afcb5e2e118";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public void addAuthenticator(HttpServletResponse response, String username) throws Exception {

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS512,SECRET).compact();

        String token = TOKEN_PREFIX + " " + JWT;
        response.addHeader(HEADER_STRING,token);
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response){

        String token = request.getHeader(HEADER_STRING);

        if (token != null){
            String tokenLimpo = token.replace(TOKEN_PREFIX,"").trim();
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(tokenLimpo)
                    .getBody().getSubject();

            if (user != null){
                Usuario usuario = ApplicationContextLoad.
                        getApplicationContext().
                        getBean(UsuarioRepository.class).findUserByLogin(user);

                if (usuario != null){
                    return new UsernamePasswordAuthenticationToken(
                            usuario.getLogin(),
                            usuario.getSenha(),
                            usuario.getAuthorities());
                }
            }
        }
        liberacaoCors(response);
        return null;
    }

    private void liberacaoCors(HttpServletResponse response){

        if (response.getHeader("Access-Control-Allow-Origin") == null){
            response.addHeader("Access-Control-Allow-Origin", "*");
        }

        if (response.getHeader("Access-Control-Allow-Headers") == null){
            response.addHeader("Access-Control-Allow-Headers", "*");
        }

        if (response.getHeader("Access-Control-Request-Headers") == null){
            response.addHeader("Access-Control-Request-Headers", "*");
        }

        if (response.getHeader("Access-Control-Allow-Methods") == null){
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
    }
}