package negocio;

public interface Subject {
    void addSubscribe(Observer o);
    void removeSubscribe(Observer o);
    void removeSubscribe(int i);

    // metodo que ira disparar a notificacao
    void changedState(Post newPost);

    // notifica os observadores
    void notifyObservers();

}
