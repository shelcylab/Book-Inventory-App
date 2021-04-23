package bookdata;

/**
 *
 * @author shelc
 */
public class Book {

    protected int serialNumber;
    protected String bookName;
    protected String authorName;
    protected String publisher;

    public Book() {
    }

    public Book(String bookName, String authorName, String publisher) {
        super();
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisher = publisher;
    }

    public Book(int serialNumber, String bookName, String authorName, String publisher) {
        super();
        this.serialNumber = serialNumber;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisher = publisher;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
