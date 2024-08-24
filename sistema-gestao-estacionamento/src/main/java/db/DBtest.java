package db;

import entities.Pessoa;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBtest {

    private static Connection conn = null;

    public static Connection getConnection(){
        if(conn == null){
            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e){
                throw new RuntimeException(e.getMessage());
            }

        }
        return conn;
    }

    public static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static void insert(Pessoa p) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO teste "
                            + "(Name)"
                            + "VALUES "
                            + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, p.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    p.setId(id);
                }
                DBtest.closeResultSet(rs);
            }else{
                throw new RuntimeException("Unexpected error! No rows affected!");
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBtest.closeStatement(st);
        }

    }

    public static Pessoa findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT teste.* "
                            + "FROM teste "
                            + "WHERE idteste = ? ");

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()){
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("idteste"));
                p.setName(rs.getString("Name"));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBtest.closeStatement(st);
            DBtest.closeResultSet(rs);
        }
    }


}
