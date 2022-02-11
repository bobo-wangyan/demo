package com.example.test;

public class A {

    private String name;
    void test1() throws Throwable{
        synchronized (this){
             try {

             }catch (Throwable e){
                 throw e;
             }
        }
    }
    synchronized void test2() throws Throwable{
        try {

        }catch (Throwable e){
            throw e;
        }
    }
    synchronized static void test3() throws Throwable{
        try {

        }catch (Throwable e){
            throw e;
        }
    }

     static void test4() throws Throwable{
        synchronized (A.class){
            try {

            }catch (Throwable e){
                throw e;
            }
        }
    }
}
