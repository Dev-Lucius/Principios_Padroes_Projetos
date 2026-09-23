package design_patters.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * SUBJECT / Entidade Observável
 *  - O chamado mantem nossa lista de Observadores e os notifica quando o status se altera
 *  - Ele so conhece a interface ObservadorChamado, nunca as classes concretas
 * 
 *  - Cada chamado têm a sua própria lista, então "NEM TODOS OS COMPONENTES PRECISAM ACOMPANHAR OS CHAMADOS"
 *  - ==> Apenas quem se inscreveu e o notificado
 */
public class Chamado {
    private final int id;
    private final String titulo;
    private StatusChamado status = StatusChamado.ABERTO;
    private final List<ObservadorChamado> observadores = new ArrayList<>();

    public Chamado(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    // Adicionando um Observador
    public void adicionarObservador(ObservadorChamado obs){
        if(!observadores.contains(obs)){
            observadores.add(obs);
        }
    }

    // Removendo um Observador
    public void removerObservador(ObservadorChamado obs){
        observadores.remove(obs);
    }

    // Notificando um Observador
    public void notificarObservadores(StatusChamado anterior){
        for(ObservadorChamado o : new ArrayList<>(observadores)){
            o.atualizar(this, anterior);
        }
    }

    // Alterando um Status
    public void alterarStatus(StatusChamado novoStatus){
        if(novoStatus == this.status){
            return;
        }

        StatusChamado anterior = this.status;
        this.status = novoStatus;
        notificarObservadores(anterior);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public StatusChamado getStatus() {
        return status;
    }


}
