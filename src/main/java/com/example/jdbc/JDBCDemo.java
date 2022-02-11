package com.example.jdbc;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 首先Connection 接口操作，提供连接数据库的操作
 * Statement 、
 */


public class JDBCDemo {

    /**
     * 反射 拿类的class
     * @param args
     * @throws Exception
     */


    public static void main(String[] args) throws Exception {


        InputStream resourceAsStream = Test.class.getClassLoader().getResourceAsStream("data.properties");

        Properties properties = new Properties();
        properties.load(resourceAsStream);
        System.out.println(properties.getProperty("path"));
        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String password = properties.getProperty("jdbc.password");
        String database = properties.getProperty("database");
        String packageName = properties.getProperty("path");
        Class.forName("jdbc:mysql://10.0.0.131:3306/");

        Connection connection = DriverManager.getConnection(url+database, user, password);

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(database, null, null, null);
        while (tables.next()){
            String tableName = tables.getString(3);

            System.out.println(tableName);


            ResultSet columns = metaData.getColumns(database, database, tableName, null);
            Map<String,String> dataMap = new HashMap<>();
            while (columns.next()){
                String columnName = columns.getString(4);
                String dataType = columns.getString(6);
                dataMap.put(columnName,dataType);
            }

            /**
             * 生成一个类的步骤
             *  1 导入包名 和相关 依赖类
             *  2 publib class XXXx {
             *  3 属性
             *  4 封装属性
             *  5 }
             */
            StringBuilder javaSourceBuilder = new StringBuilder();
            javaSourceBuilder.append("package " + packageName +"; \n\n");
            Set<Map.Entry<String, String>> entries = dataMap.entrySet();
            for (Map.Entry<String, String> entry:entries){
                String nessaryImport = Util.IMPORT_PACKAGE_MAP.get(entry.getValue());
                if(null != nessaryImport){
                    javaSourceBuilder.append(nessaryImport + "\n");
                }
            }
            javaSourceBuilder.append("\n");
            String generateClazzName = Util.generateClazzName(tableName);

            javaSourceBuilder.append("public class " + generateClazzName + " { \n\n");

            for (Map.Entry<String, String> entry:entries){
                String fieldName = Util.generateFieldName(entry.getKey());
                String fieldType = Util.SQL_TYPE_2_JAVA_TYPE_MAP.get(entry.getValue());
                javaSourceBuilder.append("\t" + "private "+ fieldType + " " + fieldName + "; \n\n");
            }

            /**
             * set/get方法
             * public void setId(Integer id){
             *     this.id = id
             * }
             *
             * public Integer getId(){
             *     return id;
             * }
             */

            for (Map.Entry<String, String> entry:entries){
                String fieldName = Util.generateFieldName(entry.getKey());
                String fieldType = Util.SQL_TYPE_2_JAVA_TYPE_MAP.get(entry.getValue());
                String methodFieldName = Util.generateMethodFieldName(entry.getKey());
                javaSourceBuilder.append("\t" + "public void set"+methodFieldName + " (" + fieldType +" "+fieldName+"){ \n");
                javaSourceBuilder.append("\t" + "    this." +fieldName + "=" +fieldName +"; \n");
                javaSourceBuilder.append("\t } \n\n");

                javaSourceBuilder.append("\t"+"public " + fieldType +" get"+methodFieldName+"(){" + "\n");
                javaSourceBuilder.append("\t"+"    return " + fieldName +"; \n");
                javaSourceBuilder.append("\t } \n\n");
            }

            javaSourceBuilder.append("}");

            String dir = Util.createDir(packageName);
            Util.writeClazz(dir + File.separatorChar+ generateClazzName +".java",javaSourceBuilder.toString());

        }


    }
}
