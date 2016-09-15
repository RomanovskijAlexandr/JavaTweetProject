package com.company;


public class Sentiments implements Outable {
    public String mood;
    public float frequence;
    public Sentiments(String mood1, float frequence){
        this.frequence = frequence;
        mood = mood1;
    }
    public  Sentiments(){};
    public String toString()
    {
        return mood + ", " + frequence;
    }

    public void print() {
        System.out.println(this.toString());
    }
}
