package br.com.batch.api.batchapi.dto;

import br.com.batch.api.batchapi.domain.Leitura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LeituraDto(
        @NotBlank String idLogger,
        float valor,
        @NotNull LocalDate data,
        Long id,
        @NotBlank String responsavel) {

    public LeituraDto(Leitura leitura){
        this(leitura.getIDLogger(), leitura.getValor(), leitura.getData(), leitura.getId(), leitura.getResponsavel());
    }
}
