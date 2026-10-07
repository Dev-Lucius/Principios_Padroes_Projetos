import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ITERATOR.
 *
 * A turma guarda os alunos em um array simples que ela mesma redimensiona.
 * Quem usa a Turma NUNCA vê esse array: só recebe um Iterator<Aluno>.
 * Se amanhã o array virar ArrayList ou LinkedList, só este arquivo muda;
 * o código cliente (Main) continua idêntico.
 *
 * Cada chamada a iterator() cria um objeto novo com a SUA posição,
 * então várias travessias podem acontecer ao mesmo tempo sem interferir.
 */
public class Turma implements Iterable<Aluno> {

    private Aluno[] alunos = new Aluno[2];
    private int quantidade = 0;

    public void adicionar(Aluno aluno) {
        if (quantidade == alunos.length) {
            alunos = Arrays.copyOf(alunos, alunos.length * 2); // cresce quando enche
        }
        alunos[quantidade++] = aluno;
    }

    @Override
    public Iterator<Aluno> iterator() {
        return new IteradorTurma();
    }

    // Classe interna privada: conhece a estrutura da Turma, mas ninguém de fora conhece o iterador concreto
    private class IteradorTurma implements Iterator<Aluno> {

        private int posicao = 0; // estado da travessia fica no iterador, não na coleção

        @Override
        public boolean hasNext() {
            return posicao < quantidade;
        }

        @Override
        public Aluno next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return alunos[posicao++];
        }
    }
}
