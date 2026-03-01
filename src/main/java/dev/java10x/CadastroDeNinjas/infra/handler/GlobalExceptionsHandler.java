package dev.java10x.CadastroDeNinjas.infra.handler;

import dev.java10x.CadastroDeNinjas.infra.exceptions.NinjaNotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(NinjaNotFoundExceptions.class)
    public ResponseEntity<String> handleNinjaNotFound(NinjaNotFoundExceptions ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
