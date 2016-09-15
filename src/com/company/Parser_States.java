package com.company;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser_States {

    public static ArrayList<State> parse (String stateCoordinate){
        Pattern nameState = Pattern.compile("[A-Z]{2}");
        ArrayList<State> stateCollectionCoordinate = new ArrayList<>();
        String[] arr=stateCoordinate.split(", \"");
        for(int i = 0; i<arr.length; i++){
            Matcher m1 = nameState.matcher(arr[i]);
            m1.find();
            State state = getState(m1.group(),arr[i]);
            stateCollectionCoordinate.add(state);
        }

        return stateCollectionCoordinate;
    }

    public static State getState(String name, String str){
        State state = new State(name);
        Pattern coordinates = Pattern.compile("(\\-?[0-9]+\\.[0-9]+)");
        Matcher m2 = coordinates.matcher(str);
        Polygon polygon = new Polygon();
        while(m2.find()) {
            Point2D.Double point = new Point2D.Double();
            point.x = Double.parseDouble(m2.group());
            m2.find();
            point.y = Double.parseDouble(m2.group());
            state.addCoordinate(point);
            polygon.addPoint((int)(point.x * 10 + 1750),(int)(-1 * point.y * 10 + 800));
        }
        state.setPolygon(polygon);

        return state;
    }
}
