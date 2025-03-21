package br.com.batch.api.batchapi.dto;

import org.springframework.validation.FieldError;

public record ErrosValidacao(String campo, String mensagem) {
    public ErrosValidacao(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }

}
