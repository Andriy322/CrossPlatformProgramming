import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException, ParseException {

        var library = new Library("books.txt", "abonements.txt");
        while (true){
            System.out.println("Виберіть завдання,яке хочете виконати: \n1 - для сортування книг за роком видання\n2 - для створення списку мейлів для розсилання\n3 - для перевірки " +
                    "кількості взятих книг визначеного автора\n4 - щоб знайти найбільшу кількість книг, що була взята читачем\n" +
                    "5 - щоб здійснити розсилання двом групам користувачів\n6 - щоб створити список боржників бібліотеки" + "\n7 - " +
                    "щоб серіалізувати колекції\n0 - для виходу з програми");
            var num = new Scanner(System.in).next();
            switch (num){
                case "1":
                    library.SortBooksByPublishedYear();
                    break;
                case "2":
                    var emails = library.GetEmailsForMailing();
                    System.out.println("Електронні пошти для нотифікації:");
                    for (var email:emails) {
                        System.out.println(email);
                    }
                    break;
                case "3":
                    System.out.println("Введіть шуканого автора: ");
                    var author = new Scanner(System.in).nextLine();
                    System.out.println("всього " + library.GetReaderCountByAuthor(author) + " книг, авторства " + author + ", що взяли читачі бібліотеки");
                    break;
                case "4":
                    System.out.print("Максимальна кількість книг, взята читачем бібліотеки: ");
                    System.out.println(library.GetMaxBooksTakenCount());
                    break;
                case "5":
                    library.MakeMailingToGroups();
                    break;
                case "6":
                    var debtors = library.GetDebtors();
                    for (var debtor:debtors) {
                        System.out.println("Боржники бібліотеки: ");
                        System.out.println(debtor.GetName());
                        System.out.println("Заборговані книги: ");
                        for (var expiredBook : debtor.GetExpiredBooks()) {
                            System.out.println("Автор: " + expiredBook.author + "  Назва: " + expiredBook.name + "   Рік видання: "
                                    + expiredBook.publishedYear + "  Протермінування у днях: " + expiredBook.GetOverdueDays());
                        }
                    }
                    break;
                case "7":
                    library.SerializeCollections();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Некоректно введене значення.");
            }
        }








    }
}