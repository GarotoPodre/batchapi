package br.com.batch.api.batchapi.controller;

import br.com.batch.api.batchapi.domain.Usuario;
import br.com.batch.api.batchapi.dto.UsuarioDto;
import br.com.batch.api.batchapi.infra.security.TokenService;
import br.com.batch.api.batchapi.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;


    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UsuarioDto dto){

        var token=new UsernamePasswordAuthenticationToken((dto.login()), dto.senha());

        var auth = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.geradorToken((Usuario)auth.getPrincipal()));
    }

}
