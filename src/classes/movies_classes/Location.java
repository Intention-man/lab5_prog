package classes.movies_classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root
public class Location {
    @Element(required=false, name = "loc_x")
    private long x;
    @Element(required=false, name = "loc_y")
    private long y;
    @Element(required=false, name = "loc_z")
    private double z;

    public Location(@Element(required=false) long x, @Element(required=false) long y, @Element(required=false) double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

//    public Location(@Element(name="x") long x, @Element(name="y") long y, @Element(name="z") double z){
//        this.x = x;
//        this.y = y;
//        this.z = z;
//    }

//    public Location(List list)
//    {
//        System.out.println("I'm in location's constructor");
//        if (list.get(0).toString().matches("\\d*")) {this.x = (long) list.get(0);};
//        if (list.get(1).toString().matches("\\d*")) {this.y = (long) list.get(1);};
//        if (list.get(2).toString().matches("\\d*.\\d*")) {this.z = (double) list.get(2);};
//    }



    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
