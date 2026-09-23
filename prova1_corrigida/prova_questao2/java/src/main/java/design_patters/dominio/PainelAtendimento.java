package design_patters.dominio;

// Observador Concreto
public class PainelAtendimento implements ObservadorChamado{

    @Override
    public void atualizar(Chamado chamado, StatusChamado statusAnterior) {
        System.out.printf("  [Painel] Chamado #%d (%s) agora esta: %s%n",
                chamado.getId(), chamado.getTitulo(), chamado.getStatus());
    }

}
