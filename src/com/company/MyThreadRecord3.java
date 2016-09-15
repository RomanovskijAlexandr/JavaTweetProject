package com.company;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyThreadRecord3 implements Runnable {
    private Scanner scanner;
    ReentrantLock locker;
    MyThreadRecord3(ReentrantLock locker){
        this.locker=locker;
        scanner = new Scanner(System.in);
    }
    public void run(){
        try {
            locker.lock();
            Record3 r3 = new Record3();
            System.out.println("Input time interval like this hh:mm:ss - hh:mm:ss");
            String timeInterval = scanner.nextLine();
            r3.compose(timeInterval);
        }finally {
            locker.unlock();
        }
    }
}
