package negocio;

public class Aluno extends Pessoa implements Observer {
    private final String nrMatricula;

    public Aluno(String nrMatricula, String nome) {
        super(nome);
        this.nrMatricula = nrMatricula;
    }


    @Override
    public void update(String message) {
        System.out.println("Aluno "+this.nrMatricula+" com nome "+nome+" recebeu o alerta do clima:"+message);
    }

}