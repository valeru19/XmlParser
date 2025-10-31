import java.io.FileWriter;
import java.io.IOException;

public class XmlGenerator {

    public static void generateXml(Library library, String filePath) throws IOException {
        StringBuilder xml = new StringBuilder();

        // Заголовок
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<library>\n");

        // Все книги
        for (Book book : library.getBooks()) {
            xml.append("  <book id=\"").append(escape(book.getId())).append("\">\n");

            xml.append("    <title>").append(escape(book.getTitle())).append("</title>\n");
            xml.append("    <author>").append(escape(book.getAuthor())).append("</author>\n");
            xml.append("    <year>").append(book.getYear()).append("</year>\n");
            xml.append("    <genre>").append(escape(book.getGenre())).append("</genre>\n");
            xml.append("    <price currency=\"").append(escape(book.getCurrency())).append("\">")
                    .append(book.getPrice()).append("</price>\n");
            xml.append("    <isbn>").append(escape(book.getIsbn())).append("</isbn>\n");

            // Publisher
            if (book.getPublisher() != null) {
                xml.append("    <publisher>\n");
                xml.append("      <name>").append(escape(book.getPublisher().getName())).append("</name>\n");

                if (book.getPublisher().getAddress() != null) {
                    xml.append("      <address>\n");
                    xml.append("        <city>").append(escape(book.getPublisher().getAddress().getCity())).append("</city>\n");
                    xml.append("        <country>").append(escape(book.getPublisher().getAddress().getCountry())).append("</country>\n");
                    xml.append("      </address>\n");
                }
                xml.append("    </publisher>\n");
            }

            // Language
            if (book.getLanguage() != null) {
                xml.append("    <language>").append(escape(book.getLanguage())).append("</language>\n");
            }

            // Translator
            if (book.getTranslator() != null) {
                xml.append("    <translator>").append(escape(book.getTranslator())).append("</translator>\n");
            }

            // Awards
            if (!book.getAwards().isEmpty()) {
                xml.append("    <awards>\n");
                for (String award : book.getAwards()) {
                    xml.append("      <award>").append((award)).append("</award>\n");
                }
                xml.append("    </awards>\n");
            }

            // Format
            if (book.getFormat() != null) {
                xml.append("    <format>").append(escape(book.getFormat())).append("</format>\n");
            }

            // Reviews
            if (!book.getReviews().isEmpty()) {
                xml.append("    <reviews>\n");
                for (Review review : book.getReviews()) {
                    xml.append("      <review>\n");
                    xml.append("        <user>").append(escape(review.getUser())).append("</user>\n");
                    xml.append("        <rating>").append(review.getRating()).append("</rating>\n");
                    xml.append("        <comment>").append(escape(review.getComment())).append("</comment>\n");
                    xml.append("      </review>\n");
                }
                xml.append("    </reviews>\n");
            }

            xml.append("  </book>\n");
        }

        xml.append("</library>\n");

        // Запись в файл
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(xml.toString());
        }
    }

    // Экранирование спецсимволов
    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}