package com.company;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadRecord2 implements Runnable {
    private Scanner scanner;
    ReentrantLock locker;
    MyThreadRecord2(ReentrantLock locker){
        this.locker=locker;
        scanner = new Scanner(System.in);
    }
    public void run(){
        try {
            locker.lock();
            Record2 r2 = new Record2();
            System.out.println("Input time interval like this hh:mm:ss - hh:mm:ss");
            String timeInterval = scanner.nextLine();
            r2.compose(timeInterval);
        }finally {
            locker.unlock();
        }
    }
}
