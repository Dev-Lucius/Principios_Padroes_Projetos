package design_patters.dominio;

public class Grafico implements Observer{
    private final String nome;
    private final int codigo;
    
    public Grafico(String nome, int codigo) {
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
        System.out.printf("\t Grafico %s Foi Avisado Sobre a Temeperatura \n", nome);
    }
}
