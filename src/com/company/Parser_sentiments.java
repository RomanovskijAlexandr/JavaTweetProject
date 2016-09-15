package com.company;


import java.util.ArrayList;

public class Parser_sentiments {
    static ArrayList<Sentiments> Parser_mood(ArrayList<String> humor){
        ArrayList<Sentiments> list_mood=new ArrayList<Sentiments>();

        for(int i=0; i<humor.size(); i++){
            Sentiments sentiments = new Sentiments();
            String[] arr = humor.get(i).split(",");
            sentiments.mood = arr[0];
            sentiments.frequence=Float.parseFloat(arr[1]);
            list_mood.add(sentiments);
        }
        return list_mood;
    }
}
