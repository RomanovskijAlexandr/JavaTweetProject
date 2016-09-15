package com.company;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertDatabase {

    public static void insertSentimets(ArrayList<Sentiments> sentiments) {
        Connection connection = null;//создание потока
        PreparedStatement preparedStatement = null;//запрос на передучу с параметром
        try {
            connection = DatabaseConnecter.getConnection();//получение соединений с базой данных
            String query = "INSERT INTO java_project.sentiments(mood, frequence) values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            for (Sentiments sentiment : sentiments) {
                preparedStatement.setString(1, sentiment.mood);
                preparedStatement.setDouble(2, sentiment.frequence);
                preparedStatement.executeUpdate();
                //System.out.println("good");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

        }
    }
    public static void insertTweets(ArrayList<Tweet> objects){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnecter.getConnection();
            for (Tweet tweet : objects){
                String query = "INSERT INTO java_project.tweets(coordinate_1,coordinate_2,number,date,text) values(?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);//запрос

                preparedStatement.setDouble(1,tweet.coordinate_1);
                preparedStatement.setDouble(2, tweet.coordinate_2);
                preparedStatement.setInt(3, tweet.number);
                java.sql.Timestamp timestamp = new java.sql.Timestamp(tweet.date.getTime());
                preparedStatement.setTimestamp(4, timestamp);
                preparedStatement.setString(5, tweet.text);
                preparedStatement.executeUpdate();
                //System.out.println("good");
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
                preparedStatement.close();
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void insertStates(ArrayList<State> states){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnecter.getConnection();
            String query = "INSERT INTO java_project.states(nameOfState,coordinates) values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            for (State state : states){
                ArrayList<Point2D.Double> coordinates = state.getCoordinates();
                String str = "";
                for (Point2D.Double coordinate : coordinates ){
                    str += "["+coordinate.x+", "+coordinate.y+"]";
                    if(coordinate != coordinates.get(coordinates.size() - 1)) str += ", ";
                }
                //str += "]";
                preparedStatement.setString(1,state.getName());
                preparedStatement.setString(2, str);
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
                preparedStatement.close();
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}


