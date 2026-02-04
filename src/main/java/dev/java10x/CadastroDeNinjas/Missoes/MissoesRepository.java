package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.data.jpa.repository.JpaRepository;

/// JPA é uma abstração para as querys do banco de dados
/// passar o modelo que quer que ele escaneie e o tipo de dado do "id" desse modelo
/// ORM object relational mapping (fica escaneando as entidades, verificando as alterações e aplicando no db)
public interface MissoesRepository extends JpaRepository<MissoesModel, Long> {
}
