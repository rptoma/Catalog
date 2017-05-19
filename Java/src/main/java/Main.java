import java.sql.*;

/**
 * Created by toma on 19/05/2017.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DBManager dbManager = new DBManager();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog?autoReconnect=true&useSSL=false", "root", "parola123");
            System.out.println(dbManager.getStudentsInscrisMaterieNote(connect));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
