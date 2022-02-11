package com.example.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {

    public static void main(String[] args) throws Exception {


        InputStream resourceAsStream = Test.class.getClassLoader().getResourceAsStream("data.properties");

        Properties properties = new Properties();
        properties.load(resourceAsStream);

        System.out.println(properties.getProperty("path"));

    }
}
