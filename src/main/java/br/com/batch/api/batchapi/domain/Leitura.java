package br.com.batch.api.batchapi.domain;

import br.com.batch.api.batchapi.dto.LeituraDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name="leitura")
@Entity(name = "Leitura")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String IDLogger;
    private float valor;
    private LocalDate data;
    private String responsavel;

    public Leitura(LeituraDto dto){
        System.out.println("Valor do DTO "+dto.toString());
        this.IDLogger=dto.idLogger();
        this.valor=dto.valor();
        this.data=dto.data();
        this.responsavel=dto.responsavel();
    }

}
