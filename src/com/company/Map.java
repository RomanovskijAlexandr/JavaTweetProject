package com.company;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Map extends JFrame {
    private final int WIDTH = 1350;
    private final int HEIGHT = 700;
    private final String TITLE = "Map States";//заголовок
    private final boolean VISIBLE = true;//видимое окно
    private ArrayList<State> states;
    public Map (ArrayList<State> states){
        this.setSize(WIDTH,HEIGHT);
        this.setTitle(TITLE);
        this.setVisible(VISIBLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.states = states;
    }

    public void paint(Graphics g) {
        //String[] colors = createColorTable();
        int i =0;
        for(State state : states) {
            //System.setProperty("color", colors[i]);
            //Color color = Color.getColor("color");
            g.setColor(new Color(255 - i*5,0,0));//RGB
            //g.setColor(color);
                g.fillPolygon(state.getPolygon());//рисует штат
            i++;
        }
    }
}
