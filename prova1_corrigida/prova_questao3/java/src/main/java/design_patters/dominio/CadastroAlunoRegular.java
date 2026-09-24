package design_patters.dominio;

public class CadastroAlunoRegular extends CadastroAluno<AlunoRegular>{

    @Override
    protected String getTipo() {
        return "Aluno Regular";
    }

    @Override
    protected void validarDadosEspecificos(AlunoRegular aluno) {
        exigir(aluno.getCursoGraduacao(), "Curso de Graducação");
        if(aluno.getSemestreIngresso() == null || !aluno.getSemestreIngresso().matches("\\d{4}\\.[12]")){
            System.out.println("Semestre de ingresso deve estar no formato AAAA.S (ex.: 2026.2).");
        }
    }

    @Override
    protected void confirmarMatricula(AlunoRegular aluno) {
        if (!aluno.atendeRequisitosCurso()) {
            System.out.println("Aluno nao atende aos requisitos do curso.");
        }
        emitirMatricula(aluno, "REG");
        System.out.println("  - Requisitos do curso verificados. Matricula confirmada.");
    }

}
