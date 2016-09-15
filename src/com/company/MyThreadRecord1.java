package com.company;

import java.util.Scanner;
import java.lang.InterruptedException;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadRecord1 implements Runnable {
    private Scanner scanner;
    ReentrantLock lock;
    MyThreadRecord1(ReentrantLock lock){
        this.lock=lock;
        scanner = new Scanner(System.in);
    }
    public void run() {
        try {
            lock.lock();
            System.out.println("Thread with record 1 start");

            Record1 r1 = new Record1();
            System.out.println("Input hashtag:");
            String hashtag = scanner.nextLine();
            r1.compose(hashtag);
        } finally {
            lock.unlock();
        }
    }
}
