import java.util.ArrayList;
import java.util.List;

public class Debtor  {

    private List<ExpiredBook> mustReturnBooks = new ArrayList<>();
    private String name;
    private String email;

    public void AddBook(ExpiredBook book){
        if(book != null)
            mustReturnBooks.add(book);
    }

    public Debtor(String name, String email){
        this.name = name;
        this.email = email;
    }

    public List<ExpiredBook> GetExpiredBooks(){
        return mustReturnBooks;
    }
    public String GetName(){ return name; }
    public int GetBooksCount(){
        return mustReturnBooks.size();
    }
}
