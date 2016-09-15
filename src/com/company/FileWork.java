package com.company;


import java.io.*;
import java.lang.String;
import java.util.ArrayList;

public class FileWork {

    public static ArrayList<String> read(String fileName) throws FileNotFoundException{

        ArrayList<String> tweet_info = new ArrayList<String>();
        try ( BufferedReader in = new BufferedReader(new FileReader(fileName))){

                String s;

                while ((s = in.readLine()) != null) {// построчно считываем файл
                    tweet_info.add(s);
                    //System.out.println("s="+s);
                }

        } catch(IOException e) {
            throw new RuntimeException(e);
        }


        return tweet_info;//Возвращаем текст с файла
    }
}
