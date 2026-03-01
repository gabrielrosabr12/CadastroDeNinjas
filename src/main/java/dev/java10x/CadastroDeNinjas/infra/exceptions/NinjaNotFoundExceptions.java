package dev.java10x.CadastroDeNinjas.infra.exceptions;

public class NinjaNotFoundExceptions extends RuntimeException{
    public NinjaNotFoundExceptions(String message){
        super(message);
    }
}
