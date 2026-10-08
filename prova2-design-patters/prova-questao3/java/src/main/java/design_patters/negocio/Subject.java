package design_patters.negocio;

public interface Subject {
    public void addObserver(Observer obs);
    public void removeObsever(Observer obs);
    public void removeObserverPos(int pos);
    public void notifyObservers();
}
