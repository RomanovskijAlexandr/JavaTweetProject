package com.company;

import java.util.Scanner;

public class RecordQuery {
    private Scanner scanner;
    public RecordQuery(){
        scanner = new Scanner(System.in);
    }

    public void createRecord1(){
        Record1 r1 = new Record1();
        System.out.println("Input hashtag:");
        String hashtag = scanner.nextLine();
        r1.compose(hashtag);
    }

    public void createRecord2(){
        Record2 r2 = new Record2();
        System.out.println("Input time interval like this hh:mm:ss - hh:mm:ss");
        String timeInterval = scanner.nextLine();
        r2.compose(timeInterval);
    }

    public void createRecord3(){
        Record3 r3 = new Record3();
        System.out.println("Input time interval like this hh:mm:ss - hh:mm:ss");
        String timeInterval = scanner.nextLine();
        r3.compose(timeInterval);
    }

}
