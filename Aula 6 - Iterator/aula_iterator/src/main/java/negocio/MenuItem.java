package negocio;

enum Segmento {
    CAFE_DA_MANHA,
    ALMOCO,
    JANTA;
}

public class MenuItem {
    private String descricao;
    private double preco;
    private boolean vegetariano;
    private Segmento segmento;

    public MenuItem(){}

    public MenuItem(String descricao, double preco, boolean vegetariano, Segmento segmento) {
        this.descricao = descricao;
        this.preco = preco;
        this.vegetariano = vegetariano;
        this.segmento = segmento;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public boolean isVegetariano() {
        return vegetariano;
    }
    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }
    public Segmento getSegmento() {
        return segmento;
    }
    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

    @Override
    public String toString() {
        return "MenuItem [descricao=" + descricao + ", preco=" + preco + ", vegetariano=" + vegetariano + ", segmento="
                + segmento + "]";
    }

    

}
