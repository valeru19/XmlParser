import java.util.ArrayList;
import java.util.List;

public class Library   {
    private List<Book> books = new ArrayList<Book>();
    // Добавление вместо сеттера потому что Library - мать списка, только она создает список книг и добавляет их туда
    // и устанавливать список целиком опасно, может все поломаться

    public void addBook(Book book) {
        books.add(book);
    }


    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }
}
