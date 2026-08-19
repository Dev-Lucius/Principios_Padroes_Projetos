package negocio;

enum Status {
    LIGADO,
    DESLIGADO
}

public class HomeTheater {
    private int potencia;
    private int qtdeCaixinhas;
    private int voltagem;
    private int tipoDolbySurrond;
    private Status status;

    public HomeTheater() {
        this.status = Status.DESLIGADO;
    }

    public void ligar(){
        this.status = Status.LIGADO;
    }

    public void desligar(){
        this.status = Status.DESLIGADO;
    }

}
