package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;


public class State implements Outable {
    private String nameOfState;
    private ArrayList<Point2D.Double> coordinates;
    private Polygon polygon;

    public State(String name_of_state){
        this.nameOfState = name_of_state;
        coordinates= new ArrayList<Point2D.Double>();
        polygon=new Polygon();
    }

    public void addCoordinate(Point2D.Double coordinate){
        coordinates.add(coordinate);
    }

    public void setPolygon(Polygon polygon){
        this.polygon=polygon;
    }
    public Polygon getPolygon(){
        return polygon;
    }
    public String getName(){
        return nameOfState;
    }
    public ArrayList<Point2D.Double> getCoordinates(){
        return coordinates;
    }
    public String toString(){
        String outString="";
        for(Point2D.Double coordinate : coordinates ){
            outString+="["+coordinate.x+", "+coordinate.y+"]";
        }
        return nameOfState+"["+outString+"]";
    }

    public void print() {
       System.out.println(this.toString());
    }
}
