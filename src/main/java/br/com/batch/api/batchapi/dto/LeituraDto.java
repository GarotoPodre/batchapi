package br.com.batch.api.batchapi.dto;

import java.time.LocalDate;

public record LeituraDto(String idLogger, float valor, LocalDate data, Long id, String responsavel) {
}
