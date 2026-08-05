package negocio;

import java.util.ArrayList;
import java.util.List;

public class Influenciador implements Subject {
    private List<Post> vetPost;
    private List<Observer> vetSeguidores;

    public Influenciador(){
        this.vetPost = new ArrayList<Post>();
        this.vetSeguidores = new ArrayList<Observer>();
    }

    @Override
    public void addSubscribe(Observer o) {
        this.vetSeguidores.add(o);
    }

    @Override
    public void removeSubscribe(Observer o) {
        this.vetSeguidores.remove(o);

    }

    @Override
    public void removeSubscribe(int i) {
        this.vetSeguidores.remove(i);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : vetSeguidores) {
            observer.update(this.vetPost.get(this.vetPost.size()-1));
        }
    }

    @Override
    public void changedState(Post newPost) {
        this.vetPost.add(newPost);
        this.notifyObservers();
    }

}

