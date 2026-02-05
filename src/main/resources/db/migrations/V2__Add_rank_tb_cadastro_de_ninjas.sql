-- Migration para adicionar a coluna de rank na tabela de cadastros

ALTER TABLE tb_cadastro_de_ninjas
    ADD COLUMN rank VARCHAR(255);