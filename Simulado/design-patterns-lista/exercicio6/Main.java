public class Main {
    public static void main(String[] args) {

        String csv = "nome,idade\nAna,20\nBeto,22";
        String json = "[{\"nome\":\"Carla\",\"idade\":21},{\"nome\":\"Diego\",\"idade\":23}]";
        String xml = "<alunos><aluno><nome>Elisa</nome><idade>24</idade></aluno>"
                   + "<aluno><nome>Fábio</nome><idade>25</idade></aluno></alunos>";

        // Mesma chamada (importar) para os três formatos
        ImportadorDados[] importadores = {
            new ImportadorCSV("alunos.csv", csv),
            new ImportadorJSON("alunos.json", json),
            new ImportadorXML("alunos.xml", xml)
        };

        for (ImportadorDados importador : importadores) {
            importador.importar();
            System.out.println("   Banco: " + importador.getBanco());
            System.out.println();
        }

        // Falha na validação: o arquivo é fechado mesmo assim (finally no template)
        System.out.println("--- CSV com registro inválido ---");
        ImportadorDados invalido = new ImportadorCSV("invalido.csv", "nome,idade\n,30");
        try {
            invalido.importar();
        } catch (IllegalStateException e) {
            System.out.println("   Erro: " + e.getMessage());
        }
    }
}
