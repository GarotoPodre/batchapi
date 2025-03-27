package br.com.batch.api.batchapi.controller;

import br.com.batch.api.batchapi.domain.Usuario;
import br.com.batch.api.batchapi.dto.UsuarioDtoAdd;
import br.com.batch.api.batchapi.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    @Transactional
    public ResponseEntity salva(@RequestBody UsuarioDtoAdd dto){
        System.out.println("valor de dto: "+dto.toString());
        if(repository.findByLogin(dto.login())==null){
            var usuario = new Usuario();
            usuario.setLogin(dto.login());
            usuario.setSenha(encoder.encode(dto.senha()));
            usuario.setRole(dto.role());
            repository.save(usuario);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
