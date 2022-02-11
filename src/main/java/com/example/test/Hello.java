package com.example.test;

public class Hello<T> {

    public static int i = 100;

    public int add(int a,int b){
        return a+b;
    }

    public void test(){
        add(1,2);
    }

    public static void eat(){
        System.out.println(i);
    }

    public static void doSomething(Hello<? extends Number> hello){

    }

    public static void main(String[] args) {
        Hello<String> hello = new Hello();

    }
}
