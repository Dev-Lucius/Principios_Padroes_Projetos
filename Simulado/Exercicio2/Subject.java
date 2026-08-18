package Exercicio2;

public interface Subject {
    
    void anexar(Observer obs);

    void separar(Observer obs);
    
    void notificarObservadores();
}
