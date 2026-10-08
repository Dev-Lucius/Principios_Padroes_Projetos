package design_patters.negocio;

public abstract class AlgoritmoMineracao {
    
    // Template Final
    public final void minerar(API api, CapturaPostsBrutos cap, FiltrarPalavras fil, SalvarBancoDados sav){
        inicializarMineracao();
        processoMineracao();
        finalizacao();
    }
                        
    public abstract void processoMineracao();

    private String inicializarMineracao(){
        return "Iniciando Mineração de Dados";
    }

    private String finalizacao(){
        return "Finalizando Mineração";
    }
}
