package negocio;

public class Arquivo /*implements Cloneable*/ {
    private String nome;
    private float tamanho;
    private String caminho;
    private String extensao;
    private Usuario dono;

    public Arquivo(){
        this.dono = new Usuario();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getTamanho() {
        return tamanho;
    }
    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }
    public String getCaminho() {
        return caminho;
    }
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    public String getExtensao() {
        return extensao;
    }
    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }
    // Desta forma n conseguimos clonar tambem o objeto de Usuario que compoe a instancia de Arquivo
    // @Override
    // public Object clone() throws CloneNotSupportedException {
    //     return super.clone();
    // // }
    public Usuario getDono() {
        return dono;
    }
    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public Arquivo clone(){
        Arquivo clone = new Arquivo();
        clone.setCaminho(this.caminho);
        clone.setExtensao(this.extensao);
        clone.setNome(this.nome);
        clone.setTamanho(this.tamanho);
        clone.setDono(new Usuario(this.dono.getNome()));
        return clone;
    }

    

}
