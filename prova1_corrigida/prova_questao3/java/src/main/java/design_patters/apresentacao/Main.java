package design_patters.apresentacao;

import design_patters.dominio.AlunoPosGraduacao;
import design_patters.dominio.AlunoRegular;
import design_patters.dominio.AlunoVisitante;
import design_patters.dominio.CadastroAlunoRegular;
import design_patters.dominio.CadastroPosGraduacao;
import design_patters.dominio.CadastroVisitante;

public class Main {
    public static void main(String[] args){

        // System.out.println("Hello World");

        System.out.println("Questão 3 - Template Method");
        
        CadastroAlunoRegular cadastroRegular = new CadastroAlunoRegular();
        CadastroPosGraduacao cadastroPos = new CadastroPosGraduacao();
        CadastroVisitante cadastroVis = new CadastroVisitante();

        // Casos de Sucesso
        cadastroRegular.cadastrar(new AlunoRegular(
                "Ana Souza", "12345678901", "Ciencia da Computacao", "2026.2", true));

        cadastroPos.cadastrar(new AlunoPosGraduacao(
                "Bruno Lima", "98765432100", "Mestrado em Engenharia de Software",
                "Prof. Helena Costa",true, true));

        cadastroVis.cadastrar(new AlunoVisitante(
                "Carla Dias", "11122233344", "Universidade do Porto", "2026.2 (1 semestre)", true));

        
        // 1° Caso de Falha
        // Dados Inválidos
        cadastroRegular.cadastrar(new AlunoRegular(
                "Melissa Sanhcez", "555666717788", "Técnica em Enfermagem", "2026.2", false));

        // 2° Caso de Falha
        // pos-graduacao sem Vaga
        cadastroPos.cadastrar(new AlunoPosGraduacao("Elisa Prado", "99988877766", "Doutorado em Computacao", "Prof. Marcos Vieira", true, false));

        // 3° Caso de Falha
        // Visitante sem Autorização
        cadastroVis.cadastrar(new AlunoVisitante(
                "Fabio Nunes", "44455566677", "Universidade de Lisboa", "2026.2 ", false));
    }
}
