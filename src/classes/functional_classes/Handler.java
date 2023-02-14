package classes.functional_classes;

import classes.movies_classes.Movie;
import classes.movies_classes.Movies;

import java.util.*;


public class Handler {

    // initialization

    private static Movies movies;
    private static List<String> commandsHistory = new ArrayList<>();
    public static void manageClass(Movies movies) {Handler.movies = movies;}

    // commands execution

    public static void addMovie(HashMap data) {
        int currentCount = movies.moviesCount();
        movies.getMovies().add(new Movie(currentCount, data));
        System.out.println(movies.getMovies());
    }

    public static void add_if_max(HashMap data) {
        boolean isMax = true;
        for (Movie movie : movies.getMovies()) {
            if (movie.getLength() >= (long) data.get(4)) {
                isMax = false;
                break;
            }
        }
        if (isMax) {
            addMovie(data);
        }
    }

    public static void add_if_min(HashMap data) {
        boolean isMin = true;
        for (Movie movie : movies.getMovies()) {
            if (movie.getLength() <= (long) data.get(4)) {
                isMin = false;
                break;
            }
        }
        if (isMin) {
            addMovie(data);
        }
    }

    public static void updateMovie(int id, HashMap data) {
        for (Movie movie : movies.getMovies()) {
            if (movie.getId() == id) {
                movie.update(data);
            }
        }
    }

    public static int sum_of_length() {
        int sum = 0;
        for (Movie movie : movies.getMovies()) {
            sum += movie.getLength();
        }
        return sum;
    }

    public static int countByOscarsCount(long enteredCount) {
        int count = 0;
        for (Movie movie : movies.getMovies()) {
            if (movie.getOscarsCount() == enteredCount) {
                count++;
            }
        }
        return count;
    }

    public static void removeById(int enteredId) {
        for (Movie movie : movies.getMovies()) {
            if (movie.getId() == enteredId) {
                movies.getMovies().remove(movie);
                break;
            }
        }
    }

    public static void removeAnyByOscarsCount(long enteredCount) {
        for (Movie movie : movies.getMovies()) {
            if (movie.getOscarsCount() == enteredCount) {
                movies.getMovies().remove(movie);
                break;
            }
        }
    }

    public static void clear() {
        movies.getMovies().clear();
    }

    public static void addCommandToHistory(String command){
        commandsHistory.add(command);
    }

    public static List<String> getLast12Commands() {
        return commandsHistory.subList(commandsHistory.size() >= 12 ? commandsHistory.size() - 12 : 0, commandsHistory.size());
    }
}