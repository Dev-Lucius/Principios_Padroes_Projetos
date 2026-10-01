package design_patters.dominio;

import java.util.ArrayList;
import java.util.List;

public class OrdenarPorNome implements EstrategiaOrdenacao {

    @Override
    public List<Produto> ordenar(List<Produto> produtos) {

        List<Produto> resultado = new ArrayList<>(produtos);

        for (int i = 0; i < resultado.size() - 1; i++) {

            for (int j = 0; j < resultado.size() - 1 - i; j++) {

                String nomeAtual =
                        resultado.get(j).getNome();

                String proximoNome =
                        resultado.get(j + 1).getNome();

                if (nomeAtual.compareToIgnoreCase(proximoNome) > 0) {

                    Produto temp = resultado.get(j);

                    resultado.set(j, resultado.get(j + 1));

                    resultado.set(j + 1, temp);
                }
            }
        }

        return resultado;
    }
}