import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private String genre;
    private double price;
    private String currency;
    private String isbn;
    private Publisher publisher;
    private String language;
    private String translator;
    private List<String> awards = new ArrayList<>();
    private String format;
    private List<Review> reviews = new ArrayList<>();

    public Book() {}

    public Book(String id, String title, String author, int year, String genre, double price,
                String currency, String isbn, Publisher publisher, String language, String translator, String format) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.price = price;
        this.currency = currency;
        this.isbn = isbn;
        this.publisher = publisher;
        this.language = language;
        this.translator = translator;
        this.format = format;
    }

//    методы для заполнения reviews и awards

    public void addAward(String award) { awards.add(award); }
    public void addReview(Review review) { reviews.add(review); }


    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher=" + publisher +
                ", language='" + language + '\'' +
                ", translator='" + translator + '\'' +
                ", awards=" + awards +
                ", format='" + format + '\'' +
                ", reviews=" + reviews +
                '}';
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Publisher getPublisher() { return publisher; }
    public void setPublisher(Publisher publisher) { this.publisher = publisher; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getTranslator() { return translator; }
    public void setTranslator(String translator) { this.translator = translator; }

    public List<String> getAwards() { return awards; }
    public void setAwards(List<String> awards) { this.awards = awards; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

}


