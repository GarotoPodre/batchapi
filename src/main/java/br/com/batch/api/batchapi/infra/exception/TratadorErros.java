package br.com.batch.api.batchapi.infra.exception;

import br.com.batch.api.batchapi.dto.ErrosValidacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404(){
        return ResponseEntity.notFound().build();

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erro400(MethodArgumentNotValidException ex){
       var erros=ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErrosValidacao::new).toList());
    }



}
