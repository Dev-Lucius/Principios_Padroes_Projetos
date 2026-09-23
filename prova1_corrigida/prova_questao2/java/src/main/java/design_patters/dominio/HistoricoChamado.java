package design_patters.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoChamado  implements ObservadorChamado{
    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Chamado chamado, StatusChamado statusAnterior) {
        String registro = String.format("Chamado #%d: %s -> %s",
                chamado.getId(), statusAnterior, chamado.getStatus());
        registros.add(registro);
        System.out.println("  [Historico] Registrado: " + registro);
    }

    public List<String> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}
