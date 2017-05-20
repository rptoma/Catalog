import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by toma on 18/05/2017.
 */
public class DBManager {

    /*
    Returneaza un ArrayList cu obiecte de tip StudentNotaMaterie reprezentand liniile din interogarea din resultSet.
    In cazul in care in select se afla o coloana nula si atribtuul din obiectul ce reprezinta coloana respectiva va fi null.
    De exemplu, Silviu Stan are coloana grupa nula, deci Student.grupa va fi null
     */
    private ArrayList<StudentNotaMaterie> toArrayStudentNotaMaterie(ResultSet resultSet) {

        Integer rowsCount = null;

        try {
            resultSet.last();
            rowsCount = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<StudentNotaMaterie> wrappedResult = new ArrayList<StudentNotaMaterie>(rowsCount);

        try {
            while (resultSet.next()) {
                Student student = new Student(resultSet);
                Materie materie = new Materie(resultSet);
                Nota nota = new Nota(resultSet);
                wrappedResult.add(new StudentNotaMaterie(student, materie, nota));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return wrappedResult;
        }
    }

    private void insertStudent(Connection connection, Student studentToInsert) throws SQLException {
        Integer id_student = studentToInsert.getId_student();
        String nume = studentToInsert.getNume();
        String prenume = studentToInsert.getPrenume();
        Integer grupa = studentToInsert.getGrupa();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into student(id_student, nume, prenume) " +
                        "values(?, ?, ?)");
        preparedStatement.setInt(1, id_student);
        preparedStatement.setString(2, nume);
        preparedStatement.setString(3, prenume);
        preparedStatement.executeUpdate();
    }

    private void insertInscris(Connection connection, Student studentToInsert, Materie materieToInsert) throws SQLException {
        Integer id_student = studentToInsert.getId_student();
        String nume = studentToInsert.getNume();
        String prenume = studentToInsert.getPrenume();
        Integer grupa = studentToInsert.getGrupa();

        String id_materie = materieToInsert.getId_materie();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into inscris(id_student, id_materie) " +
                        "values(?, ?)");
        preparedStatement.setInt(1, id_student);
        preparedStatement.setString(2, id_materie);
        preparedStatement.executeUpdate();
    }

    private void insertMaterie(Connection connection, Student studentToInsert, Materie materieToInsert) throws SQLException {

        String id_materie = materieToInsert.getId_materie();
        Integer nr_credite = materieToInsert.getNr_credite();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into materie(id_materie, nr_credite) " +
                        "values(?, ?)");
        preparedStatement.setString(1, id_materie);
        preparedStatement.setInt(2, nr_credite);
        preparedStatement.executeUpdate();
    }

    private void insertNota(Connection connection, Student studentToInsert, Materie materieToInsert, Nota notaToInsert) throws SQLException {
        Integer id_student = studentToInsert.getId_student();
        String id_materie = materieToInsert.getId_materie();
        Integer nota = notaToInsert.getNota();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into note(id_student, id_materie, nota)" +
                        "values(?, ?, ?)");
        preparedStatement.setInt(1, id_student);
        preparedStatement.setString(2, id_materie);
        preparedStatement.setInt(3, nota);
        preparedStatement.execute();
    }
    /*
    Primeste o conexiune SQL, un obiect de tip student, un obiect de tip materie si un obiecte de tip nota si insereaza
    datele in baza de date.
    In caz de succes, se returneaza true.
    Altfel, se returneaza false.
     */

    private Materie getMaterie(Connection connection, String id_materie) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select " +
                            "* " +
                         "from " +
                            "materie " +
                         "where " +
                            "lower(id_materie) = " + '"' + id_materie.toLowerCase() + '"');
            if (resultSet.next() == true) {
                return new Materie(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public void insertStudentInscrisMaterieNote(Connection connection, Student studentToInsert, String id_materie, Nota notaToInsert) throws SQLException {

        try {
            Materie materieToInsert = getMaterie(connection, id_materie);
            connection.setAutoCommit(false);
            insertStudent(connection, studentToInsert);
            //insertMaterie(connection, studentToInsert, materieToInsert);
            insertInscris(connection, studentToInsert, materieToInsert);
            insertNota(connection, studentToInsert, materieToInsert, notaToInsert);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit(true);
            throw e;
        }
        finally {
            connection.setAutoCommit(true);
        }
    }

    /*
     Primeste o conexiune SQL, iar prin intermediul acelei conexiuni, selecteaza toti studentii cu materiile la
     care sunt inscrisi si notele pe care le au.
     Returneaza un ArrayList cu obiecte de tip StudentNotaMaterie reprezentand liniile din interogarea din resultSet
     */
    public ArrayList<StudentNotaMaterie> getStudentMaterieNote(Connection connect) {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(
                    "select " +
                            "id_student, nume, prenume, grupa, id_materie, nr_credite, nota " +
                         "from " +
                            "student s join inscris using(id_student) " +
                            "join materie using(id_materie) " +
                            "join note using(id_student, id_materie) " +
                            "order by grupa, nume, prenume");
            return toArrayStudentNotaMaterie(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}