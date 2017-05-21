import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private ArrayList<StudentNotaMaterie> toArrayStudentNotaMaterie(ResultSet resultSet) throws SQLException {

        resultSet.last();
        Integer rowsCount = resultSet.getRow();
        resultSet.beforeFirst();

        ArrayList<StudentNotaMaterie> wrappedResult = new ArrayList<StudentNotaMaterie>(rowsCount);

        while (resultSet.next()) {
            Student student = new Student(resultSet);
            Materie materie = new Materie(resultSet);
            Nota nota = new Nota(resultSet);
            wrappedResult.add(new StudentNotaMaterie(student, materie, nota));
        }

        return wrappedResult;
    }

    private ArrayList<Materie> toArrayMaterie(ResultSet resultSet) throws SQLException {

        resultSet.last();
        Integer rowsCount = resultSet.getRow();
        resultSet.beforeFirst();

        ArrayList<Materie> wrappedResult = new ArrayList<Materie>(rowsCount);

        while (resultSet.next()) {
            Materie materie = new Materie(resultSet);
            wrappedResult.add(materie);
        }

        return wrappedResult;
    }

    private void insertStudent(Connection connection, Student studentToInsert) throws SQLException {
        Integer id_student = studentToInsert.getId_student();
        String nume = studentToInsert.getNume();
        String prenume = studentToInsert.getPrenume();
        Integer grupa = studentToInsert.getGrupa();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into student(id_student, nume, prenume, grupa) " +
                        "values(?, ?, ?, ?)");
        preparedStatement.setInt(1, id_student);
        preparedStatement.setString(2, nume);
        preparedStatement.setString(3, prenume);
        preparedStatement.setObject(4, grupa);
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
    Face o interogare a bazei de date si returneaza un ArrayList reprezentant toate materiile ce se afla in baza de date.
    Functie creata pentru meniu drop-down(HTML <select>)
     */
    private Materie getMaterie(Connection connection, String id_materie) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select " +
                            "* " +
                         "from " +
                            "materie " +
                         "where " +
                                "lower(id_materie) = ?");

            preparedStatement.setString(1, id_materie.toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Materie(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public ArrayList<Materie> getMaterii(Connection connect) throws SQLException {

        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select " +
                        "* " +
                     "from " +
                        "materie " +
                     "order by id_materie");
        return toArrayMaterie(resultSet);
    }

    /*
    Primeste o conexiune SQL si un obiect de tip Student, un String ce reprezinta id-ul materiei si un obiect de tip Nota.
    In cazul in care cererea de inserare nu are sens (din cauza obiectelor primite de functie), se va arunca o exceptie.
     */
    public void insertStudentInscrisMaterieNote(Connection connection, Student studentToInsert, String id_materie, Nota notaToInsert) throws SQLException {

        try {
            Materie materieToInsert = getMaterie(connection, id_materie);
            connection.setAutoCommit(false);

            try {
                insertStudent(connection, studentToInsert);
            }
            catch (SQLException e) {
                if(e.getErrorCode() != 1062) {
                    throw e;
                }
            }
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
    public ArrayList<StudentNotaMaterie> getStudentMaterieNote(Connection connect) throws SQLException {

        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(
                 "select " +
                         "id_student, nume, prenume, grupa, id_materie, nr_credite, nota " +
                      "from " +
                         "student s join inscris using(id_student) " +
                         "join materie using(id_materie) " +
                         "join note using(id_student, id_materie) " +
                         "order by nume, prenume, grupa");
        return toArrayStudentNotaMaterie(resultSet);
    }
}