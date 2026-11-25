package design_patters.apresentacao;

import design_patters.dominio.Goblin;
import design_patters.dominio.Inimigo;
import design_patters.dominio.Orc;
import design_patters.dominio.RegistroPrototipos;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        // Criando o registro
        RegistroPrototipos registro = new RegistroPrototipos();

        // Registrando os protótipos
        registro.registrar("orc", new Orc());
        registro.registrar("goblin", new Goblin());


        // Criando clones de Orc
        Inimigo orc1 = registro.criar("orc");
        Inimigo orc2 = registro.criar("orc");


        // Modificando apenas o segundo Orc
        orc2.getHabilidades().add("Fúria");


        // Criando um Goblin
        Inimigo goblin1 = registro.criar("goblin");


        // Exibindo os resultados
        System.out.println("=== ORC 1 ===");

        System.out.println("Nome: " + orc1.getNome());
        System.out.println("Vida: " + orc1.getVida());
        System.out.println("Habilidades: " + orc1.getHabilidades());


        System.out.println("\n=== ORC 2 ===");

        System.out.println("Nome: " + orc2.getNome());
        System.out.println("Vida: " + orc2.getVida());
        System.out.println("Habilidades: " + orc2.getHabilidades());


        System.out.println("\n=== GOBLIN ===");

        System.out.println("Nome: " + goblin1.getNome());
        System.out.println("Vida: " + goblin1.getVida());
        System.out.println("Habilidades: " + goblin1.getHabilidades());
    }
}