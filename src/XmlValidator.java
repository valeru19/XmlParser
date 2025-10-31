import java.util.List;

public class XmlValidator {

    public static boolean validateLibrary(Library library) {
        if (library == null) {
            System.err.println("ОШИБКА: Library не может быть null");
            return false;
        }

        List<Book> books = library.getBooks();
        if (books == null) {
            System.err.println("ОШИБКА: Список книг не может быть null");
            return false;
        }

        boolean allValid = true;

        for (int i = 0; i < books.size(); i++) {
            if (!validateBook(books.get(i), i + 1)) {
                allValid = false;
            }
        }

        return allValid;
    }

    private static boolean validateBook(Book book, int index) {
        if (book == null) {
            System.err.println("ОШИБКА: Книга #" + index + " не может быть null");
            return false;
        }

        boolean valid = true;

        if (isEmpty(book.getId())) {
            System.err.println("ОШИБКА: Книга #" + index + ": id обязателен");
            valid = false;
        }

        if (isEmpty(book.getTitle())) {
            System.err.println("ОШИБКА: Книга #" + index + " (id=" + book.getId() + "): title обязателен");
            valid = false;
        }

        if (isEmpty(book.getAuthor())) {
            System.err.println("ОШИБКА: Книга #" + index + " (id=" + book.getId() + "): author обязателен");
            valid = false;
        }

        if (book.getYear() <= 0) {
            System.err.println("ОШИБКА: Книга #" + index + " (id=" + book.getId() + "): year должен быть > 0");
            valid = false;
        }

        if (isEmpty(book.getGenre())) {
            System.err.println("ОШИБКА: Книга #" + index + " (id=" + book.getId() + "): genre обязателен");
            valid = false;
        }

        if (book.getPrice() <= 0) {
            System.err.println("ОШИБКА: Книга #" + index + " (id=" + book.getId() + "): price должен быть > 0");
            valid = false;
        }

        if (isEmpty(book.getCurrency())) {
            System.err.println("ОШИБКА: Книга #" + index + " (id=" + book.getId() + "): currency обязателен");
            valid = false;
        }

        if (isEmpty(book.getIsbn())) {
            System.err.println("ОШИБКА: Книга #" + index + " (id=" + book.getId() + "): isbn обязателен");
            valid = false;
        }

        // Publisher
        if (book.getPublisher() != null) {
            if (!validatePublisher(book.getPublisher(), index)) {
                valid = false;
            }
        }

        // Reviews
        List<Review> reviews = book.getReviews();
        if (reviews != null) {
            for (int j = 0; j < reviews.size(); j++) {
                if (!validateReview(reviews.get(j), index, j + 1)) {
                    valid = false;
                }
            }
        }

        return valid;
    }

    private static boolean validatePublisher(Publisher publisher, int bookIndex) {
        boolean valid = true;

        if (isEmpty(publisher.getName())) {
            System.err.println("ОШИБКА: Книга #" + bookIndex + ": publisher.name обязателен");
            valid = false;
        }

        if (publisher.getAddress() != null) {
            if (isEmpty(publisher.getAddress().getCity())) {
                System.err.println("ОШИБКА: Книга #" + bookIndex + ": publisher.address.city обязателен");
                valid = false;
            }
            if (isEmpty(publisher.getAddress().getCountry())) {
                System.err.println("ОШИБКА: Книга #" + bookIndex + ": publisher.address.country обязателен");
                valid = false;
            }
        }

        return valid;
    }

    private static boolean validateReview(Review review, int bookIndex, int reviewIndex) {
        if (review == null) {
            System.err.println("ОШИБКА: Книга #" + bookIndex + ", отзыв #" + reviewIndex + ": не может быть null");
            return false;
        }

        boolean valid = true;

        if (isEmpty(review.getUser())) {
            System.err.println("ОШИБКА: Книга #" + bookIndex + ", отзыв #" + reviewIndex + ": user обязателен");
            valid = false;
        }

        if (review.getRating() < 1 || review.getRating() > 5) {
            System.err.println("ОШИБКА: Книга #" + bookIndex + ", отзыв #" + reviewIndex + " (user=" + review.getUser() + "): rating должен быть от 1 до 5");
            valid = false;
        }

        if (isEmpty(review.getComment())) {
            System.err.println("ОШИБКА: Книга #" + bookIndex + ", отзыв #" + reviewIndex + " (user=" + review.getUser() + "): comment обязателен");
            valid = false;
        }

        return valid;
    }

    // Проверка на null или пустую строку
    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}