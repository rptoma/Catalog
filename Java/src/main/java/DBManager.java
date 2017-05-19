import com.mysql.jdbc.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;


/**
 * Created by toma on 18/05/2017.
 */
public class DBManager {

    private String writeResultSet(ResultSet resultSet) throws SQLException {

        String result = "";

        while (resultSet.next()) {
            String prenume = resultSet.getString("prenume");
            String nume = resultSet.getString("nume");
            String nume_materie = resultSet.getString("nume_materie");
            String nota = resultSet.getString("nota");
            result = result + prenume + " " + nume + " " + nume_materie + " " + nota + "\n";
        }

        return result;
    }

    public String getStudentsInscrisMaterieNote(Connection connect) {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("" +
                    "select" +
                    "   * " +
                    "from " +
                    "   student s join inscris using(id_student)" +
                    "   join materie using(id_materie)" +
                    "   join note using(id_student, id_materie);");
            return writeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}