package com.example.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Util {


    public static final Map<String,String> SQL_TYPE_2_JAVA_TYPE_MAP = new HashMap<>();

    public static final Map<String,String> IMPORT_PACKAGE_MAP = new HashMap<>();

    static {
        SQL_TYPE_2_JAVA_TYPE_MAP.put("BIGINT","Long");
        SQL_TYPE_2_JAVA_TYPE_MAP.put("INT","Integer");
        SQL_TYPE_2_JAVA_TYPE_MAP.put("DECIMAL","BigDecimal");
        SQL_TYPE_2_JAVA_TYPE_MAP.put("VARCHAR","String");
        SQL_TYPE_2_JAVA_TYPE_MAP.put("CHAR","String");
        SQL_TYPE_2_JAVA_TYPE_MAP.put("LONGBLOB","String");
        SQL_TYPE_2_JAVA_TYPE_MAP.put("DATETIME","Timestamp");
        SQL_TYPE_2_JAVA_TYPE_MAP.put("TIMESTAMP","Timestamp");
        SQL_TYPE_2_JAVA_TYPE_MAP.put("DATE","Date");


        IMPORT_PACKAGE_MAP.put("DATE","import java.sql.Date;");
        IMPORT_PACKAGE_MAP.put("DATETIME","import java.sql.Timestamp;");
        IMPORT_PACKAGE_MAP.put("TIMESTAMP","import java.sql.Timestamp;");
        IMPORT_PACKAGE_MAP.put("DECIMAL","import java.math.BigDecimal;");
    }




    /**
     *
     * @param tableName  t_order -> TOrder 首字母大写
     * @return
     */
    public static String generateClazzName(String tableName){

        String[] s = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length;i++){
            String s1 = s[i].substring(0, 1).toUpperCase();
            sb.append(s[i].replaceFirst( s[i].substring(0, 1),s1));
        }
        return sb.toString();
    }

    public static String generateFieldName(String columnName){

        String[] s = columnName.split("_");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length;i++){
            String s1 = "";
            if( i == 0){
                s1 = s[i].substring(0, 1).toLowerCase();
            }else {
                s1 = s[i].substring(0, 1).toUpperCase();
            }
            s1 = s1 + s[i].substring(1,s[i].length());
            sb.append(s1);
        }
        return sb.toString();
    }

    public static String generateMethodFieldName(String columnName){

        String[] s = columnName.split("_");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length;i++){
            String s1 = "";
            s1 = s[i].substring(0, 1).toUpperCase();
            s1 = s1 + s[i].substring(1,s[i].length());
            sb.append(s1);
        }
        return sb.toString();
    }

    /**
     * 包的路径
     * @param path
     */
    public static String createDir(String path){

        try {
            String[] split = path.split("\\.");
            String filePath = new File("").getCanonicalPath() + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar + "java";
            for(int i =0 ;i<split.length;i++){
                filePath = filePath + File.separatorChar + split[i];
            }
            File file = new File(filePath);
            file.mkdirs();
            path =file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    public static void writeClazz(String path,String clazzContent){

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(clazzContent.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(  generateFieldName("t_order_info"));

    }
}
