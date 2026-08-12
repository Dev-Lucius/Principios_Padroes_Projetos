package negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// abstract class
public abstract class Exportacao {
    public static final String DELIMITADOR = ";";
    protected List<Pessoa> vetPessoa;

    public Exportacao(){
        this.vetPessoa = new ArrayList<Pessoa>();
    }

    // template method -> dividir em etapas um processo/algoritmo e ser final
    public final void exportar(){
        // to lendo sempre csv -> leitura n varia
        leitura();
        // a transformacao eh variante -> varia
        String resultado = transformacao();
        // a gravacao varia de vez em quando -> 99 n muda
        gravacao(resultado);
    }

    // invariante/fixo -> n quero que ninguem mexa: nem a classe filha, nem o main
    private void leitura() {
        try (BufferedReader br = new BufferedReader(new FileReader("template_method/src/main/resources/dados.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITADOR); // Basic splitting
                Pessoa pessoa = new Pessoa();
                pessoa.setId(Integer.parseInt(values[0]));
                pessoa.setNome(values[1]);
                this.vetPessoa.add(pessoa);
            }
        } catch (IOException e) { e.printStackTrace(); }

    }

    // 99 das vezes fixo, mas tem aquele 1 porcento safadao...(Ex: pdf)
    protected void gravacao(String resultado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("exportado."+this.getClass().getSimpleName().replace("Exportacao", "").toLowerCase()))) {
            writer.write(resultado);
            System.out.println("Successfully wrote to the file using BufferedWriter.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // aqui cada subclasse tem a responsabilidade de resolver o b.o -> eh totalmente variante.
    protected abstract String transformacao();

}
