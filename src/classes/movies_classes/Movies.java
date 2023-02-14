package classes.movies_classes;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.*;


@Root
public class Movies {
    @ElementList
    private HashSet<Movie> moviesList = new HashSet<>();
    @Attribute
    private java.util.Date initializationDate;



    public Movies(){
        this.initializationDate = new Date();
    }
    public int moviesCount(){
        return moviesList.size();
    }

    public HashSet<Movie> getMovies(){
        return moviesList;
    }
    public List<Movie> getSortedMovies() {
        List<Movie> sortedList = new ArrayList<>(moviesList);
        sortedList.sort(new Comparator<Movie>() {
            public int compare(Movie o1, Movie o2) {
                return (Integer.compare(o1.getId() - o2.getId(), 0));
            }
        });
        return sortedList;
    }

    public Date getInitializationDate() {
        return initializationDate;
    }

}
