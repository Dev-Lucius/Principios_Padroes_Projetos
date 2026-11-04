package dominio;

public class Compactar extends DecoradorServico{

    public Compactar(Servico servico) {
        super(servico);
    }

    public void compactar(String dados){
        System.out.println("[LOG] Compactando Dados: " + dados);
    }
}
