import java.io.Serializable;
import java.util.Date;

public class Book implements Comparable<Book>, Serializable {
    String author;
    String name;
    int publishedYear;
    transient Date whenTaken;
    transient Date whenShouldReturn;
    transient Date whenBookIsActuallyReturned;

    public Book(String author, String name, int publishedYear){
        this.author = author;
        this.name = name;
        this.publishedYear = publishedYear;
    }

    public void SetWhenTaken(Date date){
        whenTaken = date;
    }
    public void SetWhenShouldReturn(Date date){
        whenShouldReturn = date;
    }
    public Book(Book book){
        this.author = book.author;
        this.name = book.name;
        this.publishedYear = book.publishedYear;
        this.whenTaken = book.whenTaken;
        this.whenShouldReturn = book.whenShouldReturn;
    }
    public Date GetWhenShouldReturn(){
        return whenShouldReturn;
    }
    @Override
    public int compareTo(Book o) {
        return this.publishedYear - o.publishedYear;
    }
}
