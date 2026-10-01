package design_patters.apresentacao;

import design_patters.dominio.EstrategiaOrdenacao;
import design_patters.dominio.OrdenarPorAvaliacao;
import design_patters.dominio.OrdenarPorMaiorPreco;
import design_patters.dominio.OrdenarPorMenorPreco;
import design_patters.dominio.OrdenarPorNome;
import design_patters.dominio.Produto;

import java.util.List;

public class Main {
public static void main(String[] args) {

        List<Produto> produtos = List.of(
                new Produto(
                        1,
                        "Notebook",
                        2.5,
                        10,
                        3500.00,
                        4.5
                ),

                new Produto(
                        2,
                        "Mouse",
                        0.2,
                        25,
                        100.00,
                        5.0
                ),

                new Produto(
                        3,
                        "Teclado",
                        0.8,
                        15,
                        250.00,
                        3.9
                ),

                new Produto(
                        4,
                        "Monitor",
                        4.0,
                        5,
                        1200.00,
                        4.5
                ),

                new Produto(
                        5,
                        "Headset",
                        0.5,
                        20,
                        300.00,
                        2.9
                )
        );

        // Lista Original
        produtos.forEach(System.out::println);

        System.out.println();

        // Criando as Estrategias
        OrdenarPorMaiorPreco maiorPreco = new OrdenarPorMaiorPreco();
        OrdenarPorMenorPreco menorPreco = new OrdenarPorMenorPreco();
        OrdenarPorAvaliacao avaliacao = new OrdenarPorAvaliacao();
        OrdenarPorNome nome = new OrdenarPorNome();

        // Lista Ordenada pelo Maior Preco
        produtos.get(0).setOrdenacao(maiorPreco);
        List<Produto> listaOrdenada = produtos.get(0).ordenarProduto(produtos);
        listaOrdenada.forEach(System.out::println);
    }
}
