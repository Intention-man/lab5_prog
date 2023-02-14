package classes.functional_classes;

import classes.movies_classes.Movies;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *    Console App Component, working with files: read, write and execute their.
 *    His methods called by Receiver.
 *    It manages collection movies, as every functional components.
 * */


public class FileWorker {

    // initialization

    private static Movies movies;
    static String xmlFileName = System.getenv("FileForLab5");
    static Path path = Paths.get(xmlFileName);
    static Serializer serializer = new Persister();
    static Scanner scanner;

    static {
        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // common

    public static Movies fill() {
        try {
            Movies movies1 = serializer.read(Movies.class, new File(xmlFileName));
            return movies1;
        } catch (Exception e) {
            System.out.println("Не существует файла с таким адресом он недоступен для чтения");
        }
        return null;
    }

    public static void manageClass(Movies movies){
        FileWorker.movies = movies;
    }

    // commands execution


    public static void readFile(Scanner fileScanner) {
        try {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Receiver.startNewAction(line);
            }
            fileScanner.close();
            Receiver.chosenScanner = new Scanner(System.in);

        } catch (Exception ex) {
            System.out.println("Не существует файла с таким адресом он недоступен для чтения");
        }
    }

    public static void save() {
        try {
            serializer.write(movies, new File(xmlFileName));
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении коллекции в файл. Узнайте у разработчика в чем дело)");
        }

    }


}
