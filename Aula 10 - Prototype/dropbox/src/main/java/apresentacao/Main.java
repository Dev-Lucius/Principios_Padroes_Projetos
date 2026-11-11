package apresentacao;

import negocio.Arquivo;
import negocio.Usuario;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Arquivo arquivo1 = new Arquivo();
        arquivo1.setNome("igor.jpg");
        arquivo1.setCaminho("/home/iapereira/");
        arquivo1.setExtensao(".jpg");
        arquivo1.setDono(new Usuario("Igor"));

        // Arquivo arquivo2 = (Arquivo) arquivo1.clone();
        Arquivo arquivo2 = arquivo1.clone();
        
        System.out.println(arquivo1.getNome());
        System.out.println(arquivo1.hashCode());
        System.out.println(arquivo1.getDono().hashCode());

        System.out.println("==============");
        
        System.out.println(arquivo2.getNome());
        System.out.println(arquivo2.hashCode());
        System.out.println(arquivo2.getDono().hashCode());

    }
}