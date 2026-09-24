package design_patters.dominio;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TEMPLATE METHOD.
 *
 * cadastrar() e FINAL: fixa a sequencia geral valida -> cadastra -> confirma
 * matricula. As etapas iguais (dados pessoais, realizar cadastro) ficam aqui,
 * implementadas uma unica vez. As etapas que dependem do tipo de aluno
 * (validar dados especificos e confirmar matricula) sao abstratas.
 *
 * O tipo generico T evita casts: cada subclasse trabalha com o seu tipo de aluno.
 */
public abstract class CadastroAluno<T extends Aluno>{

    private static final AtomicInteger SEQUENCIA = new AtomicInteger(100);

    /**
     * Metodo Template
     *  - Esqueleto do Algoritmo
    */
    public final void cadastrar(T aluno){
        System.out.println(" Iniciando Cadastro de " + aluno.getNome());        
        validarDados(aluno);        // 1. validar os dados
        realizarCadastro(aluno);    // 2. realizar o cadastro
        confirmarMatricula(aluno);  // 3. confirmar a matricula
        enviarBoasVindas(aluno);    // hook opcional
        System.out.println("  => Cadastro concluido. Matricula: " + aluno.getMatricula() + "\n");
    }

    // ---------- etapa 1: parte comum + parte especifica ----------
    private void validarDados(T aluno) {
        validarDadosPessoais(aluno);        // comum
        validarDadosEspecificos(aluno);     // varia
        System.out.println("  - Dados validados.");
    }

    private void validarDadosPessoais(Aluno aluno) {
        exigir(aluno.getNome(), "Nome");
        if (aluno.getCpf() == null || !aluno.getCpf().matches("\\d{11}")) {
            System.out.println("CPF deve ter 11 digitos.");
        }
    }

    // ---------- etapa 2: igual para todos ----------
    private void realizarCadastro(T aluno) {
        System.out.println(" - Cadastro realizado no sistema academico.");
    }

    // ---------- pontos de variacao (subclasses implementam) ----------
    protected abstract String getTipo();

    /** Lanca IllegalArgumentException se os dados do tipo de aluno forem invalidos. */
    protected abstract void validarDadosEspecificos(T aluno);

    /** Verifica as condicoes do tipo de aluno; lanca IllegalStateException se nao atendidas. */
    protected abstract void confirmarMatricula(T aluno);

    // ---------- hook: comportamento padrao, sobrescrevivel ----------
    protected void enviarBoasVindas(T aluno) {
        System.out.println("  - E-mail de boas-vindas enviado.");
    }

    // ---------- utilitarios reutilizados pelas subclasses ----------
    protected final void emitirMatricula(Aluno aluno, String prefixo) {
        aluno.setMatricula(prefixo + "-" + SEQUENCIA.incrementAndGet());
    }

    protected final void exigir(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            System.out.println(campo + " e obrigatorio.");
        }
    }
}
