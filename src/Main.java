import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            // Парсинг library.xml
            Library library = XmlParser.parserLibrary("resources/library.xml");
            System.out.println("Parsed library: ");
            System.out.println(library);

            // Генерация xml
            XmlGenerator.generateXml(library, "resources/library_generated.xml");
            System.out.println("XML сгенерирован: resources/library_generated.xml");

            // Валидация xml
            if (XmlValidator.validateLibrary(library)) {
                System.out.println("Валидация пройдена успешно!");
                XmlGenerator.generateXml(library, "resources/library_valid.xml");
            } else {
                System.out.println("Валидация НЕ пройдена. XML не будет создан.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}