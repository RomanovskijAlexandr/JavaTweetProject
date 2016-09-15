package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
    private static String fileName = /*"D:\\java\\trends\\data\\all_tweets.txt";*/"D:\\all_tweets2.txt";
    private static String Sentim = "D:\\sentiments.csv";
    private static String State = "D:\\states.json";
    public static void main(String[] args) throws IOException  {

        /*Scanner scan = new Scanner(System.in);
        System.out.print("input hashtag: ");
        String hashtag=scan.nextLine();*/
        ReentrantLock locker = new ReentrantLock();

        ArrayList<String> textFromFile = FileWork.read(fileName);
        ArrayList<Tweet> tweets=Parser_tweet_info.Parse_tweet(textFromFile);
        InsertDatabase.insertTweets(tweets);
        for(Tweet tweet: tweets){
            tweet.print();
        }
        new Thread(new MyThreadRecord1(locker),"MyThread1").start();
        //Record1.createRecord1(hashtag, tweets);

        String stateCoordinate = ReadStates.read(State);
        ArrayList<State> states=Parser_States.parse(stateCoordinate);
        InsertDatabase.insertStates(states);
        for(State state : states){
           state.print();
        }
        new Thread(new MyThreadRecord3(locker),"MyThread3").start();

        ArrayList<String> mood = FileWork.read(Sentim);
        ArrayList<Sentiments> allMood= Parser_sentiments.Parser_mood(mood);
        InsertDatabase.insertSentimets(allMood);
        for(Sentiments sentiments: allMood){
            sentiments.print();
        }
        new Thread(new MyThreadRecord2(locker),"MyThread2").start();

        Map mapStates = new Map(states);
        mapStates.repaint();

        //RecordQuery rq = new RecordQuery();
        //rq.createRecord1();
        //rq.createRecord2();
        //rq.createRecord3();*/
    }
}
