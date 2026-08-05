package negocio;

public class Post {
    private String title;
    private String text;
    // private LocalDateTime dateTime;

    public Post(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "Post [title=" + title + ", text=" + text + "]";
    }

    
    

}
