package design_patters.dominio;

public class SistemaAlerta implements Observer{
    private final String nome;
    private final int codigo;
    
    public SistemaAlerta(String nome, int codigo) {
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
        System.out.printf("\t Sistema Alerta %s Foi Avisado Sobre a Temeperatura \n", nome);
    }
}
