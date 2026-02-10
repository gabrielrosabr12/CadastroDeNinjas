package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {
    /// Somente uma classe qualquer que terá os mesmos atributos da entidade , DTO vai conversar com Service
    private Long id;
    private String nome;
    private int idade;
    private String email;
    private String imgUrl;
    private String rank;
    private MissoesModel missoes;

}
