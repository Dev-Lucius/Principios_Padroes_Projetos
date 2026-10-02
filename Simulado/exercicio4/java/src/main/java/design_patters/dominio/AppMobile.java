package design_patters.dominio;

public class AppMobile implements Observer{
    private final String nome;
    private final int codigo;
    
    public AppMobile(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public void atualizar(EstacaoMeteorologica estMet) {
        System.out.printf("\t Painel %s Foi Avisado Sobre a Temeperatura \n", nome);
    }
}
