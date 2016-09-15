package com.company;


import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record3 implements Recordable <String> {
    private final Pattern TIME_PATTERN = Pattern.compile("(([0-2][0-9]):([0-6][0-9]):([0-6][0-9]))");

    public void compose(String timeInterval) {

        ArrayList<Tweet> tweets = SelectDatabase.selectTweets();
        ArrayList<State> states = SelectDatabase.selectStates();
        int[] numOfTweets = new int[states.size()];
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        Matcher matcher = TIME_PATTERN.matcher(timeInterval);
        matcher.find();
        int time1 = convertToSeconds(matcher.group());
        matcher.find();
        int time2 = convertToSeconds(matcher.group());
        if (time2 <= time1)
            time2 += convertToSeconds("23:59:59");
        int i = 0;

        for (State state : states) {
            for (Tweet tweet : tweets) {
                String tweetTime = timeFormat.format(tweet.date);
                int time3 = convertToSeconds(tweetTime);
                if (time3 <= time1)
                    time3 += convertToSeconds("23:59:59");
                int x = (int) (tweet.coordinate_2 * 10 + 1750);
                int y = (int) (-1 * tweet.coordinate_1 * 10 + 750);
                if (state.getPolygon().contains(x, y) && time3 >= time1 && time3 <= time2)
                    numOfTweets[i]++;
            }
            i++;
        }
        Arrays.sort(numOfTweets);
        int max = numOfTweets[numOfTweets.length - 1];

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(".//record3.txt"))) {
            for (int j = 0; j < numOfTweets.length; j++) {
                if (numOfTweets[j] == max) {
                    State state = states.get(j);
                    bw.write("more number of tweets: " + max + " published in the state: " + state.getName());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int convertToSeconds(String time)
    {
        int sec = 0;
        Matcher matcher = TIME_PATTERN.matcher(time);
        matcher.find();
        sec += Integer.parseInt(matcher.group(2)) * 3600 + Integer.parseInt(matcher.group(3)) * 60 + Integer.parseInt(matcher.group(4));
        return sec;
    }
}
