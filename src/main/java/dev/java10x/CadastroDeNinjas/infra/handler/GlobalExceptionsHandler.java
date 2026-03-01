package dev.java10x.CadastroDeNinjas.infra.handler;

import dev.java10x.CadastroDeNinjas.infra.exceptions.MissaoNotFoundExceptions;
import dev.java10x.CadastroDeNinjas.infra.exceptions.NinjaNotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(NinjaNotFoundExceptions.class)
    public ModelAndView handleNinjaNotFound(NinjaNotFoundExceptions ex) {
        ModelAndView modelAndView = new ModelAndView("erro");

        modelAndView.addObject("mensagem", ex.getMessage());

        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }

    @ExceptionHandler(MissaoNotFoundExceptions.class)
    public ResponseEntity<String> handleMissaoNotFound(MissaoNotFoundExceptions ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
