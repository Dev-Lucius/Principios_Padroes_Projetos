package design_patters.dominio;

public class CadastroVisitante extends CadastroAluno<AlunoVisitante>{
    
    @Override
    protected String getTipo() { return "Aluno visitante"; }

    @Override
    protected void validarDadosEspecificos(AlunoVisitante aluno) {
        exigir(aluno.getInstituicaoOrigem(), "Instituicao de origem");
        exigir(aluno.getPeriodoPermanencia(), "Periodo de permanencia");
    }

    @Override
    protected void confirmarMatricula(AlunoVisitante aluno) {
        if (!aluno.isAutorizacaoParaAtividades()) {
            System.out.println("Nao existe autorizacao para participacao nas atividades academicas.");
        }
        emitirMatricula(aluno, "VIS");
        System.out.println("  - Autorizacao para atividades verificada. Matricula confirmada.");
    }
}
