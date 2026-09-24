package design_patters.dominio;

public class AlunoRegular extends Aluno{

    private final String cursoGraduacao;
    private final String semestreIngresso; // ex.: "2026.2"
    private final boolean atendeRequisitosCurso;

    public AlunoRegular(String nome, String cpf, String cursoGraduacao,
                        String semestreIngresso, boolean atendeRequisitosCurso) {
        super(nome, cpf);
        this.cursoGraduacao = cursoGraduacao;
        this.semestreIngresso = semestreIngresso;
        this.atendeRequisitosCurso = atendeRequisitosCurso;
    }

    public String getCursoGraduacao() {
        return cursoGraduacao;
    }

    public String getSemestreIngresso() {
        return semestreIngresso;
    }

    public boolean atendeRequisitosCurso() {
        return atendeRequisitosCurso;
    }
}
