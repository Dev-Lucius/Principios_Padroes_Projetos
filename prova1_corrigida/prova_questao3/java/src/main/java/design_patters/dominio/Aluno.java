package design_patters.dominio;

// Dados Pessoais Comuns a todos os Tipos de Aluno
public class Aluno {
    
    private final String nome;
    private final String cpf;
    private String matricula;
    
    protected Aluno(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
