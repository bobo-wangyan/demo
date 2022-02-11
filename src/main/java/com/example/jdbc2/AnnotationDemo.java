package com.example.jdbc2;


import java.lang.annotation.*;


/**
 *
 * @Target({ElementType.TYPE}) 指明这个类能用在哪里
 * @Retention(RetentionPolicy.RUNTIME)  这个注解在什么时候起作用
 * @Documented  这个注解形成文档
 * @Inherited 表明这个注解是否能被继承
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@interface Table{

 String value();
}


@Table("dddd")
public class AnnotationDemo {


    public static void main(String[] args) {


        Class<AnnotationDemo> clazz = AnnotationDemo.class;
        Table ta = clazz.getAnnotation(Table.class);
        System.out.println(ta.value());

//        Annotation[] annotations = AnnotationDemo.class.getAnnotations();
//
//        for (Annotation annotation:annotations){
//            System.out.println(annotation);
//            Class<? extends Annotation> clazz = annotation.annotationType();
//
//            System.out.println(  clazz);
//
//        }

    }
}
