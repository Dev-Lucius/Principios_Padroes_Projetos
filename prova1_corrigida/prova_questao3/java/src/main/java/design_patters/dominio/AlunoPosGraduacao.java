package design_patters.dominio;

public class AlunoPosGraduacao extends Aluno{

    private final String cursoPosGraduacao;
    private final String orientador;
    private final boolean aprovadoNoProcessoSeletivo;
    private final boolean vagaDisponivel;

    public AlunoPosGraduacao(String nome, String cpf, String cursoPosGraduacao, String orientador, boolean aprovadoNoProcessoSeletivo, boolean vagaDisponivel) {
        super(nome, cpf);
        this.cursoPosGraduacao = cursoPosGraduacao;
        this.orientador = orientador;
        this.aprovadoNoProcessoSeletivo = aprovadoNoProcessoSeletivo;
        this.vagaDisponivel = vagaDisponivel;

    }    

    public String getCursoPosGraduacao() {
        return cursoPosGraduacao;
    }

    public String getOrientador() {
        return orientador;
    }

    public boolean isAprovadoNoProcessoSeletivo() {
        return aprovadoNoProcessoSeletivo;
    }

    public boolean isVagaDisponivel() {
        return vagaDisponivel;
    }

}
