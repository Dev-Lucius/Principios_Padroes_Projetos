package design_patters.dominio;

public class Usuario {
    private final String nome;
    private final Perfil perfil;
    
    public Usuario(String nome, Perfil perfil) {
        this.nome = nome;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    
}
