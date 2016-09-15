package com.company;
import java.util.ArrayList;
import java.util.Date;


public class Tweet implements Outable {
    public double coordinate_1;
    public double coordinate_2;
    public int number;
    public Date date;
    public String text;
    public Tweet (double coordinate_1,double coordinate_2,Date date,String text, int number ){

        this.coordinate_2 = coordinate_2;
        this.coordinate_1 = coordinate_1;
        this.date = date;
        this.text = text;
        this.number = number;
    }
    public Tweet(){};

    public String toString()
    {
        return "["+coordinate_1 + ", "+ coordinate_2  + "]"+" "+number+ " " + date.toString() + " " + text;
    }

    public double getSumMood(ArrayList<Sentiments> sentiments){
        double weight = 0;
        String[] words = text.toLowerCase().split(" ");
        for (int i = 0; i < words.length; i++ ){
            int left = 0;
            int right = sentiments.size() - 1;
            while (left < right){
                int mid = left + (right - left) / 2;
                if (words[i].compareTo(sentiments.get(mid).mood.toLowerCase()) == 0) {
                    weight += sentiments.get(mid).frequence;
                    break;
                }
                if (words[i].compareTo(sentiments.get(mid).mood.toLowerCase()) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
        }
        return weight;
    }
    public void print() {

        System.out.println(this.toString());
    }
}
