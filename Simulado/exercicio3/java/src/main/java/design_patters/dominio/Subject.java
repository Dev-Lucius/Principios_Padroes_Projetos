package design_patters.dominio;

public interface Subject {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObserver();
}
