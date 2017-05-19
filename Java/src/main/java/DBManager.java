import com.mysql.jdbc.*;

import java.net.Inet4Address;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by toma on 18/05/2017.
 */
public class DBManager {

    private ArrayList<StudentNotaMaterie> writeResultSet(ResultSet resultSet) {

        ResultSetMetaData rsmd = null;
        Integer rowsCount = null;

        try {
            resultSet.last();
            rowsCount = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(rowsCount);

        ArrayList<StudentNotaMaterie> wrappedResult = new ArrayList<StudentNotaMaterie>(rowsCount);

        try {
            while (resultSet.next()) {
                Integer result_id_student = (Integer)resultSet.getObject("id_student");
                String result_nume = (String)resultSet.getObject("nume");
                String result_prenume = (String)resultSet.getObject("prenume");

                Integer result_id_materie = (Integer)resultSet.getObject("id_materie");
                String result_nume_materie = (String)resultSet.getObject("nume_materie");

                Integer result_grupa = (Integer)resultSet.getObject("grupa");
                Integer result_nr_credite = (Integer)resultSet.getObject("nr_credite");

                Integer result_nota = (Integer)resultSet.getObject("nota");

                Student student = new Student(result_id_student, result_prenume, result_nume, result_grupa);
                Materie materie = new Materie(result_id_materie, result_nume_materie, result_nr_credite);
                Nota nota = new Nota(result_nota);

                wrappedResult.add(new StudentNotaMaterie(student, materie, nota));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return wrappedResult;
        }
    }

    public void insertStudent(){

    }

    public ArrayList<StudentNotaMaterie> getStudentsInscrisMaterieNote(Connection connect) {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(
                    "select " +
                            "id_student, nume, prenume, grupa, id_materie, nume_materie, nr_credite, nota " +
                         "from " +
                            "student s join inscris using(id_student) " +
                            "join materie using(id_materie) " +
                            "join note using(id_student, id_materie) " +
                            "order by grupa, nume, prenume");
            return writeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}