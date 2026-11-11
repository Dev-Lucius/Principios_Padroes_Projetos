package negocio;

import java.util.HashMap;
import java.util.Map;

public class RepositoryArquivo {
    private static Map<String, Arquivo> repository = new HashMap<String, Arquivo>();

    public RepositoryArquivo(){

        Arquivo imagemJPG = new Arquivo();
        imagemJPG.setExtensao(".jpg");
        imagemJPG.setCaminho("/home/iapereira/Pictures");
        RepositoryArquivo.repository.put("imagemJPG", imagemJPG);


        Arquivo videoAVI = new Arquivo();
        videoAVI.setExtensao(".avi");
        videoAVI.setCaminho("/home/iapereira/Videos/");
        RepositoryArquivo.repository.put("videoAVI", videoAVI);
    }

    public Arquivo getArquivo(String tipoArquivo) {
        try {
            return RepositoryArquivo.repository.get(tipoArquivo).clone();
        } catch (Exception e) {
            System.out.println("Deu xabum! N existe este tipo de arquivo!"+tipoArquivo);
        }
        return null;

    }

    public void addTipoArquivo(String nome, Arquivo arquivo) {
        RepositoryArquivo.repository.put(nome, arquivo);
    }
}
