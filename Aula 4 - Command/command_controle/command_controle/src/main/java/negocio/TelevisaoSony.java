package negocio;

public class TelevisaoSony {
    private int volumeAtual;
    private int voltagem;
    private float polegadas;
    ///....
    /// 
    private boolean taLigada;
    
    public int getVolumeAtual() {
        return volumeAtual;
    }
    public void setVolumeAtual(int volumeAtual) {
        this.volumeAtual = volumeAtual;
    }
    public int getVoltagem() {
        return voltagem;
    }
    public void setVoltagem(int voltagem) {
        this.voltagem = voltagem;
    }
    public float getPolegadas() {
        return polegadas;
    }
    public void setPolegadas(float polegadas) {
        this.polegadas = polegadas;
    }
    public boolean isTaLigada() {
        return taLigada;
    }
    public void setTaLigada(boolean taLigada) {
        this.taLigada = taLigada;
    }

    

}
