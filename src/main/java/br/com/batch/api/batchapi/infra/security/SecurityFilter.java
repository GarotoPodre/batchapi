package br.com.batch.api.batchapi.infra.security;

import br.com.batch.api.batchapi.domain.Usuario;
import br.com.batch.api.batchapi.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Classe que tem como finalidade, interceptar a requisição e validar o token,  antes de chegar ao controller
 *
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    var tokenJWT=recuperToken(request); 

   if (tokenJWT != null) {
       var subject = tokenService.getSubject(tokenJWT);
       var role = tokenService.getRole(tokenJWT); // obtendo papel
       System.out.println("Valor de tokenJWT: "+tokenJWT.toString());
       System.out.println("Valor de subject: "+subject.toString());
       System.out.println("Valor de role: "+role.toString());
       var usuario=(Usuario)repository.findByLogin(subject);
       usuario.setRole(role);//Adicionando o papel


       var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
       SecurityContextHolder.getContext().setAuthentication(authentication);

   }


    //Encaminha para o próximo filtro
    filterChain.doFilter(request, response);

    }

    private String recuperToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
            //throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
        }

    return null;
    }
}
