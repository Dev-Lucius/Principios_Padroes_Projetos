package design_patters.dominio;

public class Computador {

    // Atributos do Computador
    private final String processador;
    private final int memoriaGb;
    private final int ssdGb;
    private final String placaVideo;
    private final int fonteWatts;
    private final boolean wifi;
    
    public Computador(Builder b) {
        this.processador = b.processador;
        this.memoriaGb = b.memoriaGb;
        this.ssdGb = b.ssdGb;
        this.placaVideo = b.placaVideo;
        this.fonteWatts = b.fonteWatts;
        this.wifi = b.wifi;
    }

    // Getters
    public String getProcessador() {
        return processador;
    }

    public int getMemoriaGb() {
        return memoriaGb;
    }

    public int getSsdGb() {
        return ssdGb;
    }

    public String getPlacaVideo() {
        return placaVideo;
    }

    public int getFonteWatts() {
        return fonteWatts;
    }

    public boolean isWifi() {
        return wifi;
    }

    @Override
    public String toString() {
        return "Computador [processador=" + processador + ", memoriaGb=" + memoriaGb + ", ssdGb=" + ssdGb
                + ", placaVideo=" + placaVideo + ", fonteWatts=" + fonteWatts + ", wifi=" + wifi + "]";
    }

    // Construindo o Builder
    public static class Builder{

        // Atributos Obrigatórios
        private final String processador;
        private final int memoriaGb;

        // Atributos opcionais com valores padrão
        private int ssdGb = 226;
        private String placaVideo = "Integrada";
        private int fonteWatts = 400;
        private boolean wifi = false;

        // Construtor apenas com processador e memoria
        // Garantimos que esses componentes sejam obrigados no Builder
        public Builder(String processador, int memoriaGb) {
            this.processador = processador;
            this.memoriaGb = memoriaGb;
        }

        // Configuração do ssd
        public Builder ssd(int gb){
            this.ssdGb = gb;
            return this;
        }

        // Configuração da Placa de video
        public Builder placaVideo(String placaVideo){
            this.placaVideo = placaVideo;
            return this;
        }

        // Configuração da Fonte
        public Builder fonteWatts(int watts){
            this.fonteWatts = watts;
            return this;
        }

        // Configuração Wi-Fi
        public Builder wifi(boolean wifi){
            this.wifi = wifi;
            return this;
        }

        // Método Build
        public Computador build(){
            if(memoriaGb < 4){
                System.out.println(
                    "Memória mínima: 4GB"
                );
            }

            if(!"integrada".equals(placaVideo) && fonteWatts < 500){
                System.out.println(
                    "Placa Dedicada Exige uma fonte de, no mínimo, 500 Watts"
                );
            }

            return new Computador(this);
        }
    }
}
