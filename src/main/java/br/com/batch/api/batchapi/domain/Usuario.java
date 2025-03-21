package br.com.batch.api.batchapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="usuario")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long Id;
        @NotBlank(message = "Login é um campo obrigatório")
        private String login;
        @NotBlank (message = "Senha é um campo obrigatório")
        private String senha;
        private String role;


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                //return List.of();
                return List.of(new SimpleGrantedAuthority(role));
        }

        @Override
        public String getPassword() {
                return senha;
        }

        @Override
        public String getUsername() {
                return login;
        }


        //Prestar atenção aqui.

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }

        public void setRole(String role) {
                this.role = role;
        }

        public void setLogin(String login) {
                this.login = login;
        }

        public void setSenha(String senha) {
                this.senha = senha;
        }
}


