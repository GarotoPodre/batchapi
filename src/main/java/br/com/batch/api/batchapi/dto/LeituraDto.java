package br.com.batch.api.batchapi.dto;

import br.com.batch.api.batchapi.domain.Leitura;

import java.time.LocalDate;

public record LeituraDto(String idLogger, float valor, LocalDate data, Long id, String responsavel) {

    public LeituraDto(Leitura leitura){
        this(leitura.getIDLogger(), leitura.getValor(), leitura.getData(), leitura.getId(), leitura.getResponsavel());
    }
}
