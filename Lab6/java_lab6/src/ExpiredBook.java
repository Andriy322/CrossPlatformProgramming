public class ExpiredBook extends Book{
    private long overdueDays;

    public ExpiredBook(Book book, long overdueDays) {
        super(book);
        this.overdueDays = overdueDays;
    }

    public long GetOverdueDays(){
        return overdueDays;
    }
}
