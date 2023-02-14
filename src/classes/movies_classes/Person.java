package classes.movies_classes;

import enums.Country;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;


public class Person {
    @Element(name="name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Element(name="passportID")
    private String passportID; //Длина строки должна быть не меньше 9, Строка не может быть пустой, Поле может быть null
    @Element(required=false)
    private Country nationality; //Поле может быть null
    @Element(required=false)
    private Location location; //Поле может быть null

    public Person(String name, String passportID, Country nationality, Location location)
    {
     this.name = name;
     this.passportID = passportID;
     this.nationality = nationality;
     this.location = location;
    }

//    public Person(List list)
//    {
//        System.out.println("I'm in location's constructor");
//        if (list.get(0).toString().matches("\\w*")) {this.name = (String) list.get(0);};
//        if (list.get(1).toString().matches("\\w*")) {this.passportID = (String) list.get(1);};
//        if (list.get(2).toString().matches("\\w*")) {this.nationality = (Country) list.get(2);};
//        if (list.get(3).l) {this.location = (Location) list.get(3);};
//    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return passportID;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }
}