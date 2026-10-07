import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        Turma turma = new Turma();
        turma.adicionar(new Aluno("Ana", "2024001"));
        turma.adicionar(new Aluno("Beto", "2024002"));
        turma.adicionar(new Aluno("Carla", "2024003")); // força o array interno a crescer

        // Código do enunciado
        Iterator<Aluno> iterator = turma.iterator();
        while (iterator.hasNext()) {
            Aluno aluno = iterator.next();
            System.out.println(aluno.getNome());
        }

        // Como Turma é Iterable, o for-each também funciona (usa o iterator por baixo)
        System.out.println("--- for-each ---");
        for (Aluno aluno : turma) {
            System.out.println(aluno);
        }

        // Dois iteradores independentes, cada um com a sua posição
        System.out.println("--- dois iteradores ao mesmo tempo ---");
        Iterator<Aluno> a = turma.iterator();
        Iterator<Aluno> b = turma.iterator();
        System.out.println("a: " + a.next().getNome());
        System.out.println("a: " + a.next().getNome());
        System.out.println("b: " + b.next().getNome()); // b começa do início
    }
}
