package design_patters.apresentacao;
import design_patters.dominio.Computador;

public class Main {

    public static void main(String[] args) {

        // ========================================
        // COMPUTADOR COMPLETO
        // ========================================

        Computador pc = new Computador.Builder("Ryzen 5", 16)
                .ssd(512)
                .placaVideo("RTX 4060")
                .fonteWatts(650)
                .wifi(true)
                .build();

        System.out.println("=== COMPUTADOR COMPLETO ===");
        System.out.println(pc);


        // ========================================
        // COMPUTADOR COM VALORES PADRÃO
        // ========================================

        Computador basico = new Computador.Builder("Core i3", 8)
                .build();

        System.out.println("\n=== COMPUTADOR BÁSICO ===");
        System.out.println(basico);


        // ========================================
        // COMPUTADOR INVÁLIDO: FONTE INSUFICIENTE
        // ========================================

        System.out.println("\n=== TESTE DE VALIDAÇÃO: FONTE ===");

        try {

            Computador invalido = new Computador.Builder("Ryzen 7", 32)
                    .placaVideo("RTX 4090")
                    .build();

            System.out.println(invalido);

        } catch (IllegalStateException e) {

            System.out.println("Erro: " + e.getMessage());

        }


        // ========================================
        // COMPUTADOR INVÁLIDO: RAM INSUFICIENTE
        // ========================================

        System.out.println("\n=== TESTE DE VALIDAÇÃO: RAM ===");

        try {

            Computador invalido = new Computador.Builder("Core i3", 2)
                    .build();

            System.out.println(invalido);

        } catch (IllegalStateException e) {

            System.out.println("Erro: " + e.getMessage());

        }

    }

}