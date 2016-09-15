package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record2 implements Recordable<String> {

        private final Pattern TIME_PATTERN = Pattern.compile("(([0-2][0-9]):([0-6][0-9]):([0-6][0-9]))");//!!!!!

        public void compose(String timeInterval){
            ArrayList<Sentiments> sentiments = SelectDatabase.selectSentiments();
            ArrayList<Tweet> tweets = SelectDatabase.selectTweets();
            Matcher matcher = TIME_PATTERN.matcher(timeInterval);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            matcher.find();
            int time1 = convertToSeconds(matcher.group());
            matcher.find();
            int time2 = convertToSeconds(matcher.group());
            if(time2 <= time1)
                time2 += convertToSeconds("23:59:59");
            double sumWeight = 0;

            for (Tweet tweet : tweets) {
                String tweetTime = timeFormat.format(tweet.date);
                Matcher matcher2 = TIME_PATTERN.matcher(tweetTime);//!!!!
                matcher2.find();
                int time3 = convertToSeconds(matcher2.group());
                if(time3 <= time1)
                    time3 += convertToSeconds("23:59:59");
                if(time3 >= time1 && time3 <= time2)
                    sumWeight += tweet.getSumMood(sentiments);
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(".//record2.txt"))){
                bw.write("Summary emotional weight at the time: " + timeInterval + " " + sumWeight);
            }
            catch (IOException ex){
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

