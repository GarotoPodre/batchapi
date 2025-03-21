package br.com.batch.api.batchapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDtoAdd(
        @NotBlank
        String login,
        @NotBlank
        String senha,
        @NotBlank
        String role) {
}
