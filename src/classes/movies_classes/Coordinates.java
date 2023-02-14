package classes.movies_classes;

import org.simpleframework.xml.Element;

public class Coordinates {

    @Element(name="coord_x")
    private Integer x;//Значение поля должно быть больше -319, Поле не может быть null
    @Element(name="coord_y")
    private int y;

    public Coordinates(@Element(name="coord_x") Integer x, @Element(name="coord_y") int y)
    {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
