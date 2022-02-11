package com.example.test;

public class DemoForClass {
     private int i = 100;


    public static void main(String[] args) {
           new DemoForClass().new NotStaticClass().print();
    }

    class NotStaticClass{
        public void print(){
            System.out.println(i);
        }
    }
}
