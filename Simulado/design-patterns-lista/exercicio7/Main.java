public class Main {

    static void mostrar(Bebida bebida) {
        System.out.printf("%s = R$ %.2f%n", bebida.descricao(), bebida.preco());
    }

    public static void main(String[] args) {

        // Exemplo do enunciado: cada linha embrulha o objeto anterior
        Bebida bebida = new Cafe();
        bebida = new Leite(bebida);
        bebida = new Chantilly(bebida);
        bebida = new Caramelo(bebida);

        System.out.println(bebida.descricao());
        System.out.println(bebida.preco());

        System.out.println();

        // Outras combinações, sem criar nenhuma classe nova
        mostrar(new Canela(new Cha()));
        mostrar(new Chantilly(new Chantilly(new Chocolate()))); // o mesmo adicional pode repetir
        mostrar(new Cafe());                                    // sem adicionais
    }
}
