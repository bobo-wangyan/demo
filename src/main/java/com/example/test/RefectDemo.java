package com.example.test;


import java.lang.reflect.Field;

class Student{
    private final String name ="木有";
    private int age;
    private  int sno;


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sno=" + sno +
                '}';
    }
}

public class RefectDemo {

    public static void main(String[] args) throws Exception {
        Student student = new Student();
        Class<? extends Student> studentClass = student.getClass();
        Class<Student> clazz = Student.class;

        Field[] fields = clazz.getFields();
        System.out.println(fields.length);
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println(declaredFields[i].getName());
            System.out.println(declaredFields[i].getType().getName());

        }
        /**
         * newInstance 必须要有无参构造器
         */
        Student stu = clazz.newInstance();
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(stu,"三生三世");
        System.out.println(stu.getName());

    }
}
