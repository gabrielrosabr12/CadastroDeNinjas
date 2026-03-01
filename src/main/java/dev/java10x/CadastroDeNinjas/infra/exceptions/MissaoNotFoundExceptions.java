package dev.java10x.CadastroDeNinjas.infra.exceptions;

public class MissaoNotFoundExceptions extends RuntimeException{
    public MissaoNotFoundExceptions(String message){
        super(message);
    }
}
