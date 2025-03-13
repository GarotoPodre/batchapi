package br.com.batch.api.batchapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="dispositivo")
@Entity(name = "Dispositivo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String idLogger;
    private String responsavel;
    private boolean ativo;
    private String sistema;
    String nome;
}
