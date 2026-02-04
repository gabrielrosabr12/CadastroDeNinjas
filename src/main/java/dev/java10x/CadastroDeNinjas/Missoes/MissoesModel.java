package dev.java10x.CadastroDeNinjas.Missoes;

import jakarta.persistence.*;
import java.util.List;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "tb_cadastro_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dificuldade")
    private String dificuldade;

    // @OneToMany - Uma missão pode ter muitos ninjas
    @OneToMany(mappedBy = "missoes") // Mapear a coluna da tabela ninja
    private List<NinjaModel> ninjas;

}
