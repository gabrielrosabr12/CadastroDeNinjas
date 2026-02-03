package dev.java10x.CadastroDeNinjas;

/// JPA significa Java Persistence API
import jakarta.persistence.*;


/// Anotar com @Entity para declarar que esta classe é uma entidade do banco de dados
/// <br>Para isso iremos baixar uma dependencia nova SpringJPA
/// <br>Dependencia para trabalharmos com persistência de dados.
/// ----
/// <br>Essa Entidade terá os atributos:
/// * nome
/// * idade
/// * email

@Entity
@Table(name = "tb_cadastro_de_ninjas")
public class NinjaModel {

    @Id //será o id primary key do db, é da biblioteca jakarta.persiste
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement a partir do numero 1, passamos a estratégia
    private Long id;
    private String nome;
    private int idade;
    private String email;


    public NinjaModel(){}

    public NinjaModel(String nome, int idade, String email){
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
