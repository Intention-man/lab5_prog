package classes.functional_classes;

import classes.movies_classes.Movies;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileWorker {

    // initialization

    private static Movies movies;
    static String xmlFile = System.getenv("FileForLab5");
    static Path path = Paths.get(xmlFile);
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
            Movies movies1 = serializer.read(Movies.class, new File(xmlFile));
            return movies1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
//                System.out.println(line);
            }
            fileScanner.close();
            Receiver.chosenScanner = new Scanner(System.in);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void save() throws Exception {
        serializer.write(movies, new File(xmlFile));
        //построчно считываем файл
//        scanner.useDelimiter(System.getProperty("line.separator"));
    }


}
