package negocio;

public class Controle {
    private Command slots[];

    public Controle () {
        this.slots = new Command[7];
        for (int i = 0; i < slots.length; i++) {
            this.slots[i] = new NoCommand();
            
        }
    }
    public void alocaSlot(Command command, int i) {
        this.slots[i] = command;
    }
    public void aperteiBotaoOn(int i){
        this.slots[i].execute();
    }
    public void aperteiBotaoOff(int i){
        this.slots[i].undo();
    }
}
