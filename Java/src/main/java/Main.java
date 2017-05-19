import java.sql.*;

/**
 * Created by toma on 19/05/2017.
 */
public class Main {

    public static void afiseazaStudentiNoteMaterii(Connection connect) {
        DBManager dbManager = new DBManager();
        System.out.println(dbManager.getStudentMaterieNote(connect));
    }

    public static void insereazaStudent(Connection connect) {
        DBManager dbManager = new DBManager();

        Student student = new Student(6,"prenume", "nume nume", 241);
        String id_materie = "POO";
        Nota nota = new Nota(10);


        try {
            dbManager.insertStudentInscrisMaterieNote(connect, student, id_materie, nota);
        } catch (SQLException e) {
            System.out.println("Nu s-a putut face inserarea.");
            System.out.println("Eroare nr. " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DBManager dbManager = new DBManager();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog?autoReconnect=true&useSSL=false", "root", "parola123");

            /*
            Asa se face un select pt toti studentii cu materii si notele lor.
             */
            afiseazaStudentiNoteMaterii(connect);

            /*
            Asa i se adauaga unui student o nota.
             */
            insereazaStudent(connect);

            afiseazaStudentiNoteMaterii(connect);



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}