package design_patters.dominio;

public class AlunoVisitante extends Aluno{
    private final String instituicaoOrigem;
    private final String periodoPermanencia;
    private final boolean autorizacaoParaAtividades;

    public AlunoVisitante(String nome, String cpf, String instituicaoOrigem, String periodoPermanencia,
            boolean autorizacaoParaAtividades) {
        super(nome, cpf);
        this.instituicaoOrigem = instituicaoOrigem;
        this.periodoPermanencia = periodoPermanencia;
        this.autorizacaoParaAtividades = autorizacaoParaAtividades;
    }

    public String getInstituicaoOrigem() {
        return instituicaoOrigem;
    }

    public String getPeriodoPermanencia() {
        return periodoPermanencia;
    }

    public boolean isAutorizacaoParaAtividades() {
        return autorizacaoParaAtividades;
    }   
}
