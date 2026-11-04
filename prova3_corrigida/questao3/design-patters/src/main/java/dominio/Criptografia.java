package dominio;

public class Criptografia extends DecoradorServico{

    public Criptografia(Servico servico) {
            super(servico);
        }
    
    @Override 
    public void baixar(String dados){
        System.out.println("[LOG] Baixando Dados Criptografados");
    }

    public void criptografar(String dados){
        System.out.println("[LOG] Criptografando Dados: " + dados);
    }

}
