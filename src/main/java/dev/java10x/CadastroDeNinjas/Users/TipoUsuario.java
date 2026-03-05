package dev.java10x.CadastroDeNinjas.Users;

public enum TipoUsuario {
    GENIN("genin"),
    CHUNIN("chunin"),
    JOUNIN("jounin"),
    ANBU("anbu"),
    KAGE("kage");

    private String role;

    TipoUsuario(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
