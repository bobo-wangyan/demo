package com.example.test;

import org.junit.Test;

public class TheadDemo {

    public void test1(){
         for(int i= 0;i<10;i++){

             new Thread(()->{

                 System.out.println( Thread.currentThread().getThreadGroup().getName());
                 System.out.println(Thread.currentThread().getName());
             }).run();
         }
    }

    public static void main(String[] args) {
        for(int i= 0;i<10;i++){

            new Thread(()->{
                System.out.println( Thread.currentThread().getThreadGroup().getName());
                System.out.println( "-"+Thread.currentThread().getName());
            }).run();
        }
    }
}
