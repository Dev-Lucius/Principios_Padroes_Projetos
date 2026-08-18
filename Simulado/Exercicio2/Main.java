package Exercicio2;

public class Main {

    public static void main(String[] args) {

        Aluno aluno = new Aluno("João");
        aluno.getNome();

        ProgressoAluno progresso = new ProgressoAluno();

        Observer email = new EmailService();
        Observer aplicativo = new AplicativoMobile();
        Observer estatisticas = new SistemaEstatisticas();
        Observer gamificacao = new SistemaGamificacao();

        progresso.anexar(email);
        progresso.anexar(aplicativo);
        progresso.anexar(estatisticas);
        progresso.anexar(gamificacao);

        progresso.atualizarProgresso(50);
    }
}