import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

public class Library {

    private HashMap<Integer, Book> bookHashMap = new HashMap<>();
    private HashMap<Integer, Subscription> subscriptionHashMap = new HashMap<>();

    public Library(String filepathBooks, String filepathSubscriptions) throws FileNotFoundException, ParseException {
        try (Stream<String> stream = Files.lines(Paths.get(filepathBooks))) {
            stream.forEach(this::ProcessBookLine);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        ReadSubscriptionsFromFile(filepathSubscriptions);
    }

    private void ReadSubscriptionsFromFile(String filepathSubscriptions) throws FileNotFoundException, ParseException {
        File file = new File(filepathSubscriptions);
        var sc = new Scanner(file);
        while (sc.hasNextLine()) {
            var words = sc.nextLine().split(", ");
            var id = words[0];
            var name = words[1];
            var email = words[2];
            var books = new ArrayList<Book>();
            for (var book: sc.nextLine().split(", ")) {
                var bookInfo = book.split(" ");
                bookHashMap.get(Integer.parseInt(bookInfo[0])).SetWhenTaken(new SimpleDateFormat("dd.MM.yyyy").parse(bookInfo[1]));
                bookHashMap.get(Integer.parseInt(bookInfo[0])).SetWhenShouldReturn(new SimpleDateFormat("dd.MM.yyyy").parse(bookInfo[2]));
                books.add(bookHashMap.get(Integer.parseInt(bookInfo[0])));
            }
            subscriptionHashMap.put(Integer.parseInt(id), new Subscription(name, email, books));
        }
    }

    private void ProcessBookLine(String bookLine){
        var strings = bookLine.split(", ");
        bookHashMap.put(Integer.parseInt(strings[0]), new Book(strings[1], strings[2], Integer.parseInt(strings[3])));
    }

    public void SortBooksByPublishedYear(){
        var bookList = new ArrayList<Book>(bookHashMap.values().stream().toList());
        Collections.sort(bookList);
        System.out.println("Список книг, посортованих за роком видання:");
        for (var book : bookList) {
            System.out.println("Назва: " + book.name + "  Автор: " + book.author + "  Рік публікації: " + book.publishedYear);
        }
    }

    public List<String> GetEmailsForMailing(){
        List<String> mailsForNotification = new ArrayList<>();
        for (var subscription: subscriptionHashMap.values()) {
            if(subscription.GetTakenBooks().size() > 2){
                mailsForNotification.add(subscription.GetEmail());
            }
        }
        return mailsForNotification;
    }

    public int GetReaderCountByAuthor(String Author){
        int counter = 0;
        for (var subscription: subscriptionHashMap.values()) {
            for (var book: subscription.GetTakenBooks()) {
                if(book.author.toLowerCase().equals(Author.toLowerCase())){
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }

    public int GetMaxBooksTakenCount(){
        int maxBooksTakenCount = 0;
        for (var subscription: subscriptionHashMap.values()) {
            if(maxBooksTakenCount < subscription.GetTakenBooks().size()){
                maxBooksTakenCount = subscription.GetTakenBooks().size();
            }
        }
        return maxBooksTakenCount;
    }

    public void MakeMailingToGroups(){
        List<Subscription> mailLibraryNews = new ArrayList<>();
        List<Subscription> mailAboutBooksReturning = new ArrayList<>();

        for (var subscription: subscriptionHashMap.values()) {
            if(subscription.GetTakenBooks().size() < 2){
                mailLibraryNews.add(subscription);
            }
            else{
                mailAboutBooksReturning.add(subscription);
            }
        }

        System.out.println("Електронні пошти для відправки новин бібліотеки: ");
        for (var subscription: mailLibraryNews) {
            System.out.println(subscription.GetEmail());
        }
        System.out.println("Електронні пошти для нагадування про вчасне повернення книг:");
        for (var subscription: mailAboutBooksReturning) {
            System.out.println(subscription.GetEmail());
        }
    }

    public List<Debtor> GetDebtors(){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date todayDate = new Date();
        var today = dateFormat.format(todayDate);
        List<Debtor> debtors = new ArrayList<>();
        for (var subscription: subscriptionHashMap.values()) {
            var debtor = new Debtor(subscription.GetName(), subscription.GetEmail());
            for (var book: subscription.GetTakenBooks()) {
                if(book.GetWhenShouldReturn().compareTo(todayDate) < 0){
                    debtor.AddBook(new ExpiredBook(book, (-1) * Duration.between(todayDate.toInstant(), book.GetWhenShouldReturn().toInstant()).toDays()));
                }
            }
            if(debtor.GetBooksCount() != 0){
                debtors.add(debtor);
            }
        }
        return debtors;
    }

    public void SerializeCollections(){
        try
        {
            ObjectOutputStream bookStream = new ObjectOutputStream(new FileOutputStream("books.dat"));
            bookStream.writeObject(new ArrayList<>(bookHashMap.values()));
            ObjectOutputStream subscriptionStream = new ObjectOutputStream(new FileOutputStream("subscriptions.dat"));
            subscriptionStream.writeObject(subscriptionHashMap.values().toArray());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        try
        {
            ArrayList<Book> deserializedBooks = new ArrayList<>();
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat")))
            {
                deserializedBooks = ((ArrayList<Book>)ois.readObject());
            }
            for (var book:deserializedBooks) {
                System.out.println(book.name + "  " + book.author + "  " + book.publishedYear);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }


}

