package design_patters.dominio;

import java.util.List;

/**
 * Produto
 *  - Produto não deve reconhecer as regras de nenhma modalidade
 *  - Ele vai delegar o Calculo para a estrategia que estiver setada
 */
public class Produto {

    private final int codigo;
    private final String nome;
    private final double pesoKg;
    private final int quantidade;
    private final double preco;
    private final double avaliacao;
    private EstrategiaOrdenacao ordenacao;

    public Produto(int codigo, String nome, double pesoKg, int quantidade, double preco, double avaliacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.pesoKg = pesoKg;
        this.quantidade = quantidade;
        this.preco = preco;
        this.avaliacao = avaliacao;
    }

    public void setOrdenacao(EstrategiaOrdenacao ordenacao){
        this.ordenacao = ordenacao;
    }

    public List<Produto> ordenarProduto(List<Produto> p){
        if(ordenacao == null){
            System.out.println("Nenhuma Estratégia de Ordenação foi Escolhida");
            return p;
        }
        return ordenacao.ordenar(p);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public double getAvaliacao() {
        return avaliacao;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Produto{");
        sb.append("codigo=").append(codigo);
        sb.append(", nome=").append(nome);
        sb.append(", pesoKg=").append(pesoKg);
        sb.append(", quantidade=").append(quantidade);
        sb.append(", preco=").append(preco);
        sb.append(", ordenacao=").append(ordenacao);
        sb.append('}');
        return sb.toString();
    }
}

