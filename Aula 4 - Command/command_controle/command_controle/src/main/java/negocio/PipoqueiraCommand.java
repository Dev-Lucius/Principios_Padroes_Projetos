package negocio;

public class PipoqueiraCommand implements Command {

    private Pipoqueira pipoqueira;

    public PipoqueiraCommand(Pipoqueira pipoqueira) {
        this.pipoqueira = pipoqueira;
    }

    @Override
    public void execute() {
        this.pipoqueira.setLigado(1);
        System.out.println("pop corn is on");
    }

    @Override
    public void undo() {
        this.pipoqueira.setLigado(0);
                System.out.println("pop corn is out");

    }

}
