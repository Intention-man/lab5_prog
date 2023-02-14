package classes.movies_classes;

import enums.Country;
import enums.MovieGenre;
import enums.MpaaRating;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Root
public class Movie {
    @Element(name="id")
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Element(name="name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Element(name="creationDate")
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Element(name="oscarsCount")
    private long oscarsCount; //Значение поля должно быть больше 0
    @Element(name="length")
    private long length; //Значение поля должно быть больше 0

    @Element(required=false, name="genre")
    private MovieGenre genre; //Поле может быть null
    @Element(required=false, name="mpaaRating")
    private MpaaRating mpaaRating; //Поле может быть null
    @Element(required=false, name="operator")
    private Person operator; //Поле может быть null

    @Element(name="coordinates")
    private Coordinates coordinates; //Поле не может быть null


    public Movie(int id, String name, Coordinates coordinates, java.util.Date creationDate, long oscarsCount, long length, MovieGenre genre, MpaaRating mpaaRating, Person operator)
    {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.length = length;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
    }

    public Movie(int id, HashMap data)
    {
        System.out.println(data);
        this.id = id;
        this.name = (String) data.get(0);
        this.coordinates = new Coordinates((Integer) data.get(1), (int) data.get(2));
        this.creationDate = new Date();
        this.oscarsCount = (long) data.get(3);
        this.length = (long) data.get(4);
        this.genre = (data.get(5) == null ?  null : (MovieGenre) data.get(5));
        this.mpaaRating = (data.get(6) == null ?  null : (MpaaRating) data.get(6));
        this.operator = new Person((String) data.get(7), (String) data.get(8), (Country) data.get(9),
                new Location((long) data.get(10), (long) data.get(11), (double) data.get(12)));
    }

    public void update(HashMap data){
        this.name = (String) data.get(0);
        this.coordinates = new Coordinates((Integer) data.get(1), (int) data.get(2));
        this.creationDate = new Date();
        this.oscarsCount = (long) data.get(3);
        this.length = (long) data.get(4);
        this.genre = (data.get(5) == null ?  null : (MovieGenre) data.get(5));
        this.mpaaRating = (data.get(6) == null ?  null : (MpaaRating) data.get(6));
//        long x;
//        long y;
//        long z;
//        Country country;
//        if (data.get(9).toString().length() > 0) {country = (Country) data.get(9);}
//        if (data.get(10).toString().length() > 0) {x = (long) data.get(10);}
        this.operator = new Person((String) data.get(7), (String) data.get(8), data.get(9) == null ? null : (Country) data.get(9),
                new Location((long) data.get(10),(long) data.get(11),(double) data.get(12)));
    }

    // getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getLength() {
        return length;
    }

    public long getOscarsCount() {
        return oscarsCount;
    }

    public String[] getInstance() {
        return new String[]{"id: " + id, "name: " + name, "coordinates:", "[x:" +
                coordinates.getX(), "y: " + coordinates.getY() + "], ", "creationDate: " + creationDate,
                "oscarsCount: " + oscarsCount, "length: " + length, "genre: " + genre, "mpaaRating: " + mpaaRating,
                "operator: [", "name: " + operator.getName(), "passportID: " + operator.getPassportID(),
                "nationality: " + operator.getNationality(), "location: ", "[x: " + operator.getLocation().getX(),
                "y: " + operator.getLocation().getY(), "z: " + operator.getLocation().getZ() + "]"};
    }
}