package design_patters.dominio;

public class CadastroPosGraduacao extends CadastroAluno<AlunoPosGraduacao>{
    @Override
    protected String getTipo() { return "Aluno de pos-graduacao"; }

    @Override
    protected void validarDadosEspecificos(AlunoPosGraduacao aluno) {
        exigir(aluno.getCursoPosGraduacao(), "Curso de pos-graduacao");
        exigir(aluno.getOrientador(), "Orientador");
    }

    @Override
    protected void confirmarMatricula(AlunoPosGraduacao aluno) {
        if (!aluno.isAprovadoNoProcessoSeletivo()) {
            System.out.println("Aluno nao foi aprovado no processo seletivo.");
        }
        if (!aluno.isVagaDisponivel()) {
            System.out.println("Nao existe vaga disponivel no curso.");
        }
        emitirMatricula(aluno, "POS");
        System.out.println("  - Aprovacao no processo seletivo e vaga verificadas. Matricula confirmada.");
    }

    @Override
    protected void enviarBoasVindas(AlunoPosGraduacao aluno) {
        // sobrescreve o hook
        System.out.println("  - E-mail enviado com os dados de contato do orientador "
                + aluno.getOrientador() + ".");
    }
}
