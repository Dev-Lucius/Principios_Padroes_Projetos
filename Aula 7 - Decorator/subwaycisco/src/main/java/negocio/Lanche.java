package negocio;

public abstract class Lanche {
    // os atributos que quero "empilhar/decorar"
    protected String descricao;
    protected double preco;
    // atributo comum
    protected String clienteNome;

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public abstract double preco();

    public abstract String descricao();

    // caso eu deseje remover o ultimo item da pilha (o ultimo condimento)
    // este metodo acaba/para a recursividade da remocao (caso eu venha remover muitas vezes)
    public Lanche removeItem() {
        // aqui pode null o retorno - mas neste caso escolhemos ficar com no minimo o Lanche basico
        return this;
    }

}
