import java.sql.*;
import java.util.ArrayList;

/**
 * Created by toma on 19/05/2017.
 */
public class ExempluMain {

    public static void afiseazaStudentiNoteMaterii(Connection connect) {
        DBManager dbManager = new DBManager();
        ArrayList<StudentNotaMaterie> result = dbManager.getStudentMaterieNote(connect);

        System.out.println("Id Nume Prenume Grupa Materie Credite Nota");
        System.out.println("------------------------------------------");
        for(StudentNotaMaterie it : result) {
            /*
             sau puteti lua fiecare atribut cu it.numeobiect.get... si sa il prelucrati cum vreti
             */
            System.out.println(it);
        }
    }

    public static void insereazaStudent(Connection connect) {
        DBManager dbManager = new DBManager();

        /*
         In loc de grupa, ar fi putut fi scris si null pt. ca grupa nu este obligatorie in baza de date.
         Adica, new Student(6,"TestNume", "TestPrenume", null)
         */
        Student student = new Student(6,"TestNume", "TestPrenume", 241);
        String id_materie = "POO";
        Nota nota = new Nota(10);


        try {
            dbManager.insertStudentInscrisMaterieNote(connect, student, id_materie, nota);
        } catch (SQLException e) {
            System.out.println("Nu s-a putut face inserarea. (eroare nr." + e.getErrorCode() +")");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DBManager dbManager = new DBManager();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String username = "root";
            String password = "parola123";
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog?autoReconnect=true&useSSL=false", username
                    , password);

            /*
            Asa se face un select pt toti studentii cu materii si notele lor.
             */
            afiseazaStudentiNoteMaterii(connect);
            System.out.println();

            /*
            Asa i se adauaga unui student o nota.
             */
            insereazaStudent(connect);
            System.out.println();

            afiseazaStudentiNoteMaterii(connect);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}