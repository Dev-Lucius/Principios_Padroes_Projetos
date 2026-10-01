package design_patters.dominio;

import java.util.List;

public interface EstrategiaOrdenacao {
    List<Produto> ordenar(List<Produto> produtos);
}
