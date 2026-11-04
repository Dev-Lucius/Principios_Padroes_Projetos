package dominio;

public class Usuario {
    private final String nome;
    private Perfil perfil;

    public Usuario(String nome, Perfil perfil){
        this.nome = nome;
        this.perfil = perfil;
    }

    public void setPerfil(Perfil perfil){
        this.perfil = perfil;
    }

    public Perfil getPerfil() {
        return perfil;
    }
}
