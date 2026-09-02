package negocio;

public class Mario {
    private int nroVidas;
    private int fase;
    private int mundo;
    private Estado estado;

    public Mario(){
        this.estado = new MarioPequeno();
    }

    public void pegarCogumelo(){
        this.estado = this.estado.pegarCogumelo(this);
    }

    public void pegarPena(){
        this.estado = this.estado.pegarPena(this);
    }

    public void levarDano(){
        this.estado = this.estado.levarDano(this);
    }

    public void pegarFlor(){
        this.estado = this.estado.pegarFlor(this);
    }



    public int getNroVidas() {
        return nroVidas;
    }
    public void setNroVidas(int nroVidas) {
        this.nroVidas = nroVidas;
    }
    public int getFase() {
        return fase;
    }
    public void setFase(int fase) {
        this.fase = fase;
    }
    public int getMundo() {
        return mundo;
    }
    public void setMundo(int mundo) {
        this.mundo = mundo;
    }
    public Estado getEstado() {
        return estado;
    }
    // public void setEstado(Estado estado) {
    //     this.estado = estado;
    // }

    

}
