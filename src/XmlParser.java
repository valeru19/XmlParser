import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XmlParser {
    public static Library parserLibrary(String xmlFilePath) throws IOException {
        Library library = new Library(); // Контейнер для книг
        BufferedReader reader = new BufferedReader(new FileReader(xmlFilePath)); // Чтение файла построчно
        String line; // Текущая строка
        Book currentBook = null; // Текущая книга
        Publisher currentPublisher = null; // Текущий издатель
        Address currentAddress = null; // Текущий адрес
        Review currentReview = null; // Текущий отзыв
        boolean inPublisher = false; // Флаг: внутри <publisher>?
        boolean inAddress = false; // Флаг: внутри <address>?
        boolean inAwards = false; // Флаг: внутри <awards>?
        boolean inReviews = false; // Флаг: внутри <reviews>?
        boolean inReview = false; // Флаг: внутри <review>?

        while ((line = reader.readLine()) != null) {
            line = line.trim(); // Удаляем пробелы
            if (line.isEmpty()) continue; // Пропускаем пустые строки

            if (line.startsWith("<book")) {
                // Начало новой книги: создаем объект и парсим id
                currentBook = new Book();
                String id = line.split("id=\"")[1].split("\"")[0];
                currentBook.setId(id);
            } else if (line.startsWith("<title>")) {
                String title = line.replace("<title>", "").replace("</title>", "");
                currentBook.setTitle(title);
            } else if (line.startsWith("<author>")) {
                String author = line.replace("<author>", "").replace("</author>", "");
                currentBook.setAuthor(author);
            } else if (line.startsWith("<year>")) {
                String year = line.replace("<year>", "").replace("</year>", "");
                currentBook.setYear(Integer.parseInt(year));
            } else if (line.startsWith("<genre>")) {
                String genre = line.replace("<genre>", "").replace("</genre>", "");
                currentBook.setGenre(genre);


            }  else if (line.contains("<price") && line.contains("</price>")) {
                // Извлекаем валюту
                int currStart = line.indexOf("currency=\"") + 10;
                int currEnd = line.indexOf("\"", currStart);
                String currency = line.substring(currStart, currEnd);
                currentBook.setCurrency(currency);

                // Извлекаем цену между > и </price>
                int valueStart = line.indexOf(">") + 1;
                int valueEnd = line.indexOf("</price>", valueStart);
                String priceStr = line.substring(valueStart, valueEnd).trim();
                currentBook.setPrice(Double.parseDouble(priceStr));

            } else if (line.startsWith("<isbn>")) {
                String isbn = line.replace("<isbn>", "").replace("</isbn>", "");
                currentBook.setIsbn(isbn); // Записываем значение



            } else if (line.startsWith("<publisher>")) {
                // Начало вложенного: создаем объект и устанавливаем флаг
                currentPublisher = new Publisher();
                inPublisher = true;

            } else if (inPublisher && line.startsWith("<name>")) {
                String name = line.replace("<name>", "").replace("</name>", "");
                currentPublisher.setName(name);

            } else if (inPublisher && line.startsWith("<address>")) {
                // Вложенный внутри publisher: создаем адрес
                currentAddress = new Address();
                inAddress = true;

            } else if (inAddress && line.startsWith("<city>")) {
                String city = line.replace("<city>", "").replace("</city>", "");
                currentAddress.setCity(city);

            } else if (inAddress && line.startsWith("<country>")) {
                String country = line.replace("<country>", "").replace("</country>", "");
                currentAddress.setCountry(country);

            } else if (inAddress && line.startsWith("</address>")) {
                // Завершение адреса: устанавливаем в publisher
                currentPublisher.setAddress(currentAddress);
                inAddress = false;

            } else if (inPublisher && line.startsWith("</publisher>")) {
                currentBook.setPublisher(currentPublisher);
                inPublisher = false;



            } else if (line.startsWith("<language>")) {
                String language = line.replace("<language>", "").replace("</language>", "");
                currentBook.setLanguage(language);

            } else if (line.startsWith("<translator>")){
                String translator = line.replace("<translator>", "").replace("</translator>", "");
                currentBook.setTranslator(translator);


            } else if (line.startsWith("<awards>")){
                inAwards = true;
            } else if (inAwards && line.startsWith("<award>")) {
                String award = line.replace("<award>", "").replace("</award>", "");
                currentBook.addAward(award);

            } else if (inAwards && line.startsWith("</awards>")){
                // Завершение списка
                inAwards = false;

            } else if (line.startsWith("<format>")){
                String format = line.replace("<format>", "").replace("</format>", "");
                currentBook.setFormat(format);

            } else if (line.startsWith("<reviews>")){
                inReviews = true;


            } else if (inReviews && line.startsWith("<review>")){
                currentReview = new Review();
                inReview = true;
            } else if (inReview && line.startsWith("<user>")){
                String user = line.replace("<user>", "").replace("</user>", "");
                currentReview.setUser(user);
            } else if (inReview && line.startsWith("<rating>")){
                String ratingStr = line.replace("<rating>", "").replace("</rating>", "");
                currentReview.setRating(Integer.parseInt(ratingStr));
            } else if (inReview && line.startsWith("<comment>")){
                String commentStr = line.replace("<comment>", "").replace("</comment>", "");
                currentReview.setComment(commentStr);
            } else if (inReview && line.startsWith("</review>")){
                currentBook.addReview(currentReview);
                inReview = false;
            } else if (inReviews && line.startsWith("</reviews>")){
                inReviews = false;


            } else if (line.startsWith("</book>")) {
                    // Завершение книги: добавляем в library
                    library.addBook(currentBook);
                    currentBook = null; // Сброс
                }
            }
            reader.close(); // Закрываем ресурс
            return library;
    }
}