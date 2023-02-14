package classes.movies_classes;

import org.simpleframework.xml.Element;



public class Location {
    @Element(required=false, name = "locX")
    private long locX;
    @Element(required=false, name = "locY")
    private long locY;
    @Element(required=false, name = "locZ")
    private double locZ;

    public Location(long locX, long locY, double locZ){
        this.locX = locX;
        this.locY = locY;
        this.locZ = locZ;
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
        return locX;
    }

    public long getY() {
        return locY;
    }

    public double getZ() {
        return locZ;
    }
}
