package com.company;

import java.awt.geom.Point2D;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class SelectDatabase {
    public static ArrayList<Tweet> selectTweets(){
        ArrayList<Tweet> tweets = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;//обычный запрос
        ResultSet resultSet = null;//создаем объект Statement, дл€ которого экземпл€ры ResultSet имеют тип только дл€ чтени€ и перемещени€ в пр€мом направлении.
        try {
            connection = DatabaseConnecter.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM java_project.tweets";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Point2D.Double point = new Point2D.Double();
                point.x = resultSet.getDouble("coordinate_1");
                point.y = resultSet.getDouble("coordinate_2");
                int num = resultSet.getInt("number");
                Timestamp timestamp = resultSet.getTimestamp("date");
                Date date = timestamp;
                String text = resultSet.getString("text");
                Tweet tweet = new Tweet(point.x,point.y,date,text,num);
                tweets.add(tweet);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            }

            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

        return tweets;
    }
    public static ArrayList<Sentiments> selectSentiments(){
        ArrayList<Sentiments> sentiments = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnecter.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM java_project.sentiments";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String mood = resultSet.getString("mood");
                float frequence = resultSet.getFloat("frequence");
                Sentiments sentiment = new Sentiments(mood,frequence);
                sentiments.add(sentiment);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

        return sentiments;
    }
    public static ArrayList<State> selectStates(){
        ArrayList<State> states = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnecter.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM java_project.states";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String nameOfState = resultSet.getString("nameOfState");
                String coordinates = resultSet.getString("coordinates");
                State state = Parser_States.getState(nameOfState,coordinates);
                states.add(state);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return states;
    }
}
