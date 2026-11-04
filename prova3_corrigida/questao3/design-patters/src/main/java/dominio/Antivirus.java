package dominio;

public class Antivirus extends DecoradorServico{

    public Antivirus(Servico servico) {
        super(servico);
    }

    public void verificarAntivirus(){
        System.out.println("Verificando Antivirus no Sistema Operacional");
    }
}
