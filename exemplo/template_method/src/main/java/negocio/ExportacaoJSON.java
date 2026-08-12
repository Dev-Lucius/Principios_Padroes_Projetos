package negocio;

import com.google.gson.Gson;

public class ExportacaoJSON extends Exportacao {

    @Override
    protected String transformacao() {
        Gson gson = new Gson();
        return gson.toJson(this.vetPessoa);

    }

}
