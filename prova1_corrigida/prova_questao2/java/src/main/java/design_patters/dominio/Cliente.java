package design_patters.dominio;

// Observador Concreto
public class Cliente implements ObservadorChamado{

    private final String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(Chamado chamado, StatusChamado statusAnterior) {
        System.out.printf(" [Cliente %s] Seu chamado #%d Mudou de %s para %s.%n", 
            nome, chamado.getId(), statusAnterior, chamado.getStatus());
    }

}
