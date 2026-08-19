package negocio;

public class ModoCinemaCommand implements Command {
    private HomeTheater homeTheater;
    private TelevisaoSony televisaoSony;
    private Pipoqueira pipoqueira;

    public ModoCinemaCommand(HomeTheater homeTheater, TelevisaoSony televisaoSony, Pipoqueira pipoqueira) {
        this.homeTheater = homeTheater;
        this.televisaoSony = televisaoSony;
        this.pipoqueira = pipoqueira;
    }

    @Override
    public void execute() {
        System.out.println("Ativando modo cinema");
        this.televisaoSony.setTaLigada(true);
        this.televisaoSony.setVolumeAtual(50);
        this.pipoqueira.setLigado(1);
        this.homeTheater.ligar();
    }

    @Override
    public void undo() {
        System.out.println("desligando modo cinema");

        this.televisaoSony.setTaLigada(false);
        // this.televisaoSony.setVolumeAtual(50);
        this.pipoqueira.setLigado(0);
        this.homeTheater.desligar();
    }

}
