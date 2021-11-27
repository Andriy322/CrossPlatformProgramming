import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Subscription implements Serializable {
    private String name;
    private String email;
    private List<Book> takenBooks = new ArrayList<>();

    public Subscription(String name, String email, List<Book> books ){
        this.name = name;
        this.email = email;
        this.takenBooks = books;
    }
    public String GetName(){ return name; }
    public List<Book> GetTakenBooks(){
        return takenBooks;
    }
    public String GetEmail(){
        return email;
    }
}
