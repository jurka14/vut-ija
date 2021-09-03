

package project;

/*
 *Class that represents one stop on a street, can be a part of a line
 */

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

/**
 *Class containing the line stop
 *@author Vojtech Jurka (xjurka08)
 *@author Samuel Krempasky (xkremp01)
 */

public class Stop implements Drawable{
    String id;
    String streetName;

    Coordinate Coor;
    Street Street;


    public Stop(String id, Coordinate Coor, Street street){
        this.id=id;
        this.Coor=Coor;
        setStreet(street);
    }

    public Stop(String id, Coordinate Coor){
        this.id=id;
        this.Coor=Coor;
    }
    public Stop(String id, Coordinate Coor, String streetName){
        this.id=id;
        this.streetName=streetName;
        this.Coor=Coor;
    }

    public Stop(String id){
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public Coordinate getCoordinate() {
        return Coor;
    }

    public void setStreet(Street s) {
        this.Street=s;
        return;
    }

    public void setStreetByName(String streetString){


    }

    public Street getStreet() {
        return Street;
    }

    public boolean equals (Object obj){
        Stop s= (Stop) obj;
        return s.getId().equals(this.getId());
    }

    @Override
    public List<Shape> getGUI() {
        return Arrays.asList(
                new Text(Coor.getX(), Coor.getY(), id),
                new Rectangle(Coor.getX(),Coor.getY(),10,10 )
        );
    }
}
