package com.company;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecter {
    //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
    private static final String URL = "jdbc:mysql://localhost:3306/java_project";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String NAME = "root";
    private static final String PASSWORD = "15041997";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            //Загружаем драйвер
            Class.forName(DRIVER);
            //Создаём соединение
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        catch (ClassNotFoundException ex){
          System.out.println(ex.getMessage());
        }

        return connection;
    }
}
