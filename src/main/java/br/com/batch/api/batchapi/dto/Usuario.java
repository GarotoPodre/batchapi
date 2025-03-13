package br.com.batch.api.batchapi.dto;

import jakarta.validation.constraints.NotBlank;

public record Usuario(@NotBlank String login) {
}
