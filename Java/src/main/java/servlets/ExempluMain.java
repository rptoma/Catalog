package servlets;

import servlets.DBManager;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by toma on 19/05/2017.
 */
public class ExempluMain {

    public static void afiseazaMaterii(Connection connect) {
        DBManager dbManager = new DBManager();
        ArrayList<Materie> result = new ArrayList<Materie>();
        try {
            result = dbManager.getMaterii(connect);
        } catch (SQLException e) {
            System.out.println("Nu s-a putut realiza interogarea (eroare nr." + e.getErrorCode() +")");
        }

        System.out.println("Nume(ID) Credite");
        System.out.println("----------------");
        for(Materie it : result) {
            /*
             sau puteti lua fiecare atribut cu it.get... si sa il prelucrati cum vreti
             */
            System.out.println(it);
        }
    }

    public static void afiseazaStudentiNoteMaterii(Connection connect) {
        DBManager dbManager = new DBManager();
        ArrayList<StudentNotaMaterie> result = new ArrayList<StudentNotaMaterie>();
        try {
            result = dbManager.getStudentMaterieNote(connect);
        } catch (SQLException e) {
            System.out.println("Nu s-a putut realiza interogarea (eroare nr." + e.getErrorCode() +")");
        }

        System.out.println("Id Nume Prenume Grupa servlets.Materie Credite servlets.Nota");
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
         Adica, new servlets.Student(6,"TestNume", "TestPrenume", null)
         */
        Student student = new Student(6,"TestNume", "TestPrenume", 241);
        String id_materie = "POO";
        Nota nota = new Nota(10);

        System.out.println("S-au inserat valorile!");

        try {
            dbManager.insertStudentInscrisMaterieNote(connect, student, id_materie, nota);
        } catch (SQLException e) {
            System.out.println("Nu s-a putut face inserarea. (eroare nr." + e.getErrorCode() +")");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Connection connect = null;

            Class.forName("com.mysql.jdbc.Driver");
            String username = "root";
            String password = "parola123";
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog?useSSL=false", username
                    , password);
        } catch (SQLException e) {
            System.out.println("Nu s-a putut realiza conexiunea!");
        }

            /*
            Asa se face un select pt toti studentii cu materii si notele lor.
             */
            afiseazaStudentiNoteMaterii(connect);
            System.out.println();

            /*
            Asa se adauaga student + materie + nota.
             */
            insereazaStudent(connect);
            System.out.println();

            afiseazaStudentiNoteMaterii(connect);
            System.out.println();

            /*
            Asa se face un select pt a afisa toate materiile.
             */
            afiseazaMaterii(connect);


    }
}