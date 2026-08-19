package negocio;

public class TvSonyCommand implements Command {

    private TelevisaoSony tvSony;

    public TvSonyCommand(TelevisaoSony tvSony) {
        this.tvSony = tvSony;
    }

    @Override
    public void execute() {
        System.out.println("Liguei");
        this.tvSony.setTaLigada(true);
    }

    @Override
    public void undo() {
        this.tvSony.setTaLigada(false);
        System.out.println("Desliguei!");
    }

}
