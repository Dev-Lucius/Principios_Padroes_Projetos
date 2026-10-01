package design_patters.dominio;

public interface Subject {
    
    public void addObserver(Observer obs);
    public void removeObsever(Observer obs);
    public void removeObserverPos(int pos);
    public void notifyObservers();
    public void changeStatus(Status status);
}
