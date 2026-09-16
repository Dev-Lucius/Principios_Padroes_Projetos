package negocio;

public abstract class Markdown {
    // os atributos que quero "empilhar/decorar"
    protected String codigo;

    public Markdown(){
        this.codigo = "";
    }

    public abstract String codigo();

    // caso eu deseje remover o ultimo item da pilha (o ultimo condimento)
    // este metodo acaba/para a recursividade da remocao (caso eu venha remover muitas vezes)
    // public Markdown removeTag() {
    //     // aqui pode null o retorno - mas neste caso escolhemos ficar com no minimo o Lanche basico
    //     return null;
    // }

}
