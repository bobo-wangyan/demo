package com.example.jdbc2.dababasepool;


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;


class DataSourceAdapter implements DataSource{

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}


class MyInvocationHandler implements InvocationHandler{
     private Connection connection;
     private List<Connection> POOL;
    public MyInvocationHandler(Connection connection,List<Connection> POOL){
        this.connection = connection;
        this.POOL = POOL;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(method.getName().equals("close")){
            POOL.add(connection);
            System.out.println("Connection was collected,连接池size:" + POOL.size());
            return null;
        }
        return method.invoke(proxy,args);
    }
}
class MyDataSource extends DataSourceAdapter {

    private final List<Connection> POOL = new LinkedList<>();

    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            for(int i =0;i<10;i++){
                Connection connection = DriverManager.getConnection("jdbc:mysql://10.0.0.131:3306/seata_order", "root", "123456");
                Connection proxyConnection = (Connection)Proxy.newProxyInstance(MyDataSource.class.getClassLoader(), new Class<?>[]{Connection.class}, new MyInvocationHandler(connection, POOL));
                POOL.add(proxyConnection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(POOL.isEmpty()){
            System.out.println("当前没连接可以使用");
            return null;
        }

        Connection connection = POOL.remove(0);
        System.out.println("get connection successful 连接池size：" + POOL.size());
        return connection;

    }


}

public class DataSourceDemo {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new MyDataSource();
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
