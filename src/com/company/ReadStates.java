package com.company;


import java.io.*;
import java.lang.String;
import java.util.ArrayList;

public class ReadStates {

    public static String read(String fileName) throws FileNotFoundException{
        String s;
        try ( BufferedReader in = new BufferedReader(new FileReader(fileName))){
            s=in.readLine();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return s;//Возвращаем текст с файла
    }
}
