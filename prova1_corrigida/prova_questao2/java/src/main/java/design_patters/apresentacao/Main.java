package design_patters.apresentacao;

import design_patters.dominio.Chamado;
import design_patters.dominio.Cliente;
import design_patters.dominio.HistoricoChamado;
import design_patters.dominio.PainelAtendimento;
import design_patters.dominio.SetorResponsavel;
import design_patters.dominio.StatusChamado;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        System.out.println("Questão 2 - Sistema de Chamados (Observer) ");

        HistoricoChamado historico = new HistoricoChamado();
        PainelAtendimento painel = new PainelAtendimento();

        // Chamado 1: todos os componentes acompanham
        Chamado c1 = new Chamado(1, "Erro ao emitir boleto");
        c1.adicionarObservador(new Cliente("Maria"));
        c1.adicionarObservador(historico);
        c1.adicionarObservador(painel);
        c1.adicionarObservador(new SetorResponsavel("Financeiro"));

        // Chamado 2: apenas historico e painel acompanham
        Chamado c2 = new Chamado(2, "Duvida sobre plano");
        c2.adicionarObservador(historico);
        c2.adicionarObservador(painel);

        System.out.println("-> Chamado 1 passa para EM_ATENDIMENTO");
        c1.alterarStatus(StatusChamado.EM_ATENDIMENTO);

        System.out.println("-> Chamado 1 e ENCERRADO");
        c1.alterarStatus(StatusChamado.ENCERRADO);

        System.out.println("-> Chamado 2 passa para EM_ATENDIMENTO (cliente e setor nao acompanham)");
        c2.alterarStatus(StatusChamado.EM_ATENDIMENTO);

        System.out.println("\nHistorico completo:");
        historico.getRegistros().forEach(r -> System.out.println("  " + r));
    }
}