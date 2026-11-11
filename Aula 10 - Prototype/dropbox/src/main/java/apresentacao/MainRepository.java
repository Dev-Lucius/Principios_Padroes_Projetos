package apresentacao;

import negocio.Arquivo;
import negocio.RepositoryArquivo;

public class MainRepository {
    public static void main(String[] args) {
        RepositoryArquivo repositoryArquivo = new RepositoryArquivo();

        Arquivo video1 = repositoryArquivo.getArquivo("videoAVI");
        video1.setCaminho("/home/aluno/videos/");
        System.out.println(video1.getCaminho());
        System.out.println(video1.hashCode());

        Arquivo video2 = repositoryArquivo.getArquivo("videoAVI");
        System.out.println(video2.getCaminho());
        System.out.println(video2.hashCode());

        Arquivo arquivoTemporario = new Arquivo();
        arquivoTemporario.setCaminho("/tmp");

        repositoryArquivo.addTipoArquivo("temporario", arquivoTemporario);


        Arquivo n = repositoryArquivo.getArquivo("temporario");
        n.setCaminho("/temporario");

        System.out.println(arquivoTemporario.hashCode());
        System.out.println(n.hashCode());



    }


    
}
