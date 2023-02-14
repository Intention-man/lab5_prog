package classes.movies_classes;

import org.simpleframework.xml.Element;

public class Coordinates {

    @Element(name="coordX")
    private Integer coordX;//Значение поля должно быть больше -319, Поле не может быть null
    @Element(name="coordY")
    private int coordY;

    public Coordinates(Integer coordX, int coordY)
    {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Integer getX() {
        return coordX;
    }

    public int getY() {
        return coordY;
    }
}
