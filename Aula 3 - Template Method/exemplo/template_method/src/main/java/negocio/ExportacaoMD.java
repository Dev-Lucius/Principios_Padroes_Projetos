package negocio;

public class ExportacaoMD extends Exportacao {

    @Override
    protected String transformacao() {
        String resultado = "# Pessoas\n";
        resultado += "----\n\n";
        if (this.vetPessoa.size() > 0) {
            for (Pessoa pessoa : vetPessoa) {
                resultado += "* "+pessoa.getNome() +"\n\n";                
            }
        }
        return resultado;
    }

}
