package design_patters.negocio;

public class Equipe implements Observer<Personagem>{

    private String nome;
    private int idEquipe;

    public Equipe(String nome, int idEquipe) {
        this.nome = nome;
        this.idEquipe = idEquipe;
    }

    public String getNome() {
        return nome;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    @Override
    public void update(Personagem p) {
        System.out.println(this.nome + ": Foi Notificado da Mudança de Dados do Personagem");
    }

    @Override
    public String toString() {
        return "Equipe [nome=" + nome + ", idEquipe=" + idEquipe + "]";
    }

}
