package negocio;

public class CardapioCafeDaManha {
    private MenuItem itens[];

    public CardapioCafeDaManha(){
        this.itens = new MenuItem[5];
        this.itens[0] = new MenuItem("Omelete", 1.99, true, Segmento.CAFE_DA_MANHA);
        this.itens[1] = new MenuItem("Café", 1.99, true, Segmento.CAFE_DA_MANHA);
        this.itens[2] = new MenuItem("Queijo Quente", 10.99, true, Segmento.CAFE_DA_MANHA);
        this.itens[3] = new MenuItem("Leite", 1.99, true, Segmento.CAFE_DA_MANHA);
        this.itens[4] = new MenuItem("Suco de Laranja", 1.99, true, Segmento.CAFE_DA_MANHA);
    }


    public MenuItem[] getItens() {
        return this.itens;
    }


}
