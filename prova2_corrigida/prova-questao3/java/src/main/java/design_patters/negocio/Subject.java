package design_patters.negocio;

/** Contrato do "fofoqueiro": quem pode ser observado. */
public interface Subject<T> {
    void addObserver(Observer<T> observer);

    void removeObserver(Observer<T> observer);

    void notifyObservers();
}
