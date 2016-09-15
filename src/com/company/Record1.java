package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Record1 implements Recordable<String> {

    public void compose(String hashtag) {
        hashtag+=" ";
        ArrayList<Tweet> foundTweets = new ArrayList<>(); // искомые твиты
        SelectDatabase sq = new SelectDatabase();
        ArrayList<Tweet> tweets = sq.selectTweets();

        for (int i = 0; i < tweets.size(); i++) {
            Tweet nowTweet = tweets.get(i);
            nowTweet.text += " ";
            tweets.set(i, nowTweet);
            if (tweets.get(i).text.contains(hashtag)) {
                foundTweets.add(tweets.get(i));
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(".//record1.doc"))) {
                if (foundTweets.size() != 0) {
                    bw.write("All tweets with hashtag " + hashtag + ':');
                    bw.newLine();
                }
                for (Tweet tweet : foundTweets) {
                    bw.write(tweet.toString());
                    bw.newLine();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
