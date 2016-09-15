package com.company;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.regex.*;
import java.lang.String;


public class Parser_tweet_info {
    public static boolean isCorrect(String str){
        Pattern pattern = Pattern.compile("\\[(\\-?[0-9]+\\.[0-9]+),.(\\-?[0-9]+\\.[0-9]+)\\].([0-9]+).([0-9]{4}\\-[0-9]{2}\\-[0-9]{2}).([0-9]{2}\\:[0-9]{2}\\:[0-9]{2}).(.+)"/*"\\[\\-?[0-9]+\\.[0-9]+, \\-?[0-9]+\\.[0-9]+\\]"*/);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static ArrayList<Tweet> Parse_tweet(ArrayList<String> str){
        ArrayList<Tweet> list_tweet=new ArrayList<Tweet>();

      try{  for(int i=0; i<str.size(); i++) {
            if (isCorrect(str.get(i))) {
                Tweet tweet = new Tweet();
                String[] arr = str.get(i).split("\t");
                //System.out.println(arr[0]);
                for (int j = 0; j < 4; j++) {
                    if (j == 0) {

                        Matcher m = Pattern.compile("[-]?\\d+\\.\\d+").matcher(arr[0]);
                        m.find();
                        tweet.coordinate_1 = Double.parseDouble(m.group());
                        //System.out.println(m.group());
                        m.find();
                        tweet.coordinate_2 = Double.parseDouble(m.group());


                    }

                    if(j==1){
                        tweet.number= Integer.parseInt(arr[j]);
                    }

                    if (j == 2) {
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = dateFormat.parse(arr[2]);
                            tweet.date = date;
                        } catch (Exception ex) {
                            System.out.print(ex.getMessage());
                        }

                    }
                    if (j == 3) {
                        tweet.text = arr[3];
                    }

                }
                list_tweet.add(tweet);
            } else {
                int j = list_tweet.size() - 1;
                Tweet prevTweet = list_tweet.get(j);
                prevTweet.text += str.get(i);
                list_tweet.set(j, prevTweet);
            }
        }

    }catch (Exception ex) {
          System.out.print(ex.getMessage());
    }
        return list_tweet;
    }

}
