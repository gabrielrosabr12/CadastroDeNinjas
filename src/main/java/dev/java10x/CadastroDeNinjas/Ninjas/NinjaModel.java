package dev.java10x.CadastroDeNinjas.Ninjas;

////JPA significa Java Persistence API
import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
@Data // Lombok cria todos os getters e os setters
@NoArgsConstructor //Lombok cria um construtor sem argumentos invisivel
@AllArgsConstructor // Lombok cria um construtor com todos argumentos
public class NinjaModel {

    @Id //será o id primary key do db, é da biblioteca jakarta.persiste
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement a partir do número 1, passamos a estratégia
    private Long id;

    private String nome;

    private int idade;

    @Column(unique = true, nullable = false) // Coluna unica, sem nulos
    private String email;

    // @ManyToOne - Um ninja tem uma unica missão
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreign Key chave estrangeira
    private MissoesModel missoes;
}

