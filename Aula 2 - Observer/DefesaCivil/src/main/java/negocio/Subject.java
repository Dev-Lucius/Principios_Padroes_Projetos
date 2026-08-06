package negocio;

public interface Subject {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void removeObserver(int pos);
    void notifyObservers();

}