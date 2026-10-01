package design_patters.dominio;

import java.util.ArrayList;
import java.util.List;

public class OrdenarPorMaiorPreco implements EstrategiaOrdenacao {

    @Override
    public List<Produto> ordenar(List<Produto> produtos) {
        List<Produto> resultado = new ArrayList<>(produtos);
        for(int i = 0; i < resultado.size() - 1; i++){

            for(int j = 0; j < resultado.size() - 1 -i; j++){

                if((resultado.get(j).getPreco()) < (resultado.get(j + 1).getPreco())){

                    Produto temp = resultado.get(j);

                    resultado.set(j, resultado.get(j + 1));

                    resultado.set(j + 1, temp);
                }
            }
        }
        return resultado;
    }
}