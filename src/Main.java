import classes.functional_classes.FileWorker;
import classes.functional_classes.Handler;
import classes.functional_classes.Receiver;
import classes.movies_classes.Movies;

import java.util.Objects;

/**
 *   Main!
 *   Starts the program. Ask FileWorker to fill a Movie's collection from file and transfers a file to functional components to management.
 *   Catches exceptions if they are.
 *
 *   Project info:
 *
 * @author Михаил Агей P3117
 * @since February 2023
 * */


public class Main {
    public static void main(String[] args) {

        try {
//            Movies movies = new Movies();
            Movies movies = FileWorker.fill();

            Receiver.manageClass(movies);
            Handler.manageClass(movies);
            FileWorker.manageClass(movies);

            Receiver.suggestNewAction();
            while (!Objects.equals(Receiver.getExecutedCommand(), "exit")) {
                Receiver.startNewAction(Receiver.getExecutedCommand());
                Receiver.suggestNewAction();
            }
        } catch (Exception e) {
            System.out.println("Проверьте, что правильно указали название (адрес) файла, из которого считывается коллекция");
        }
    }
}