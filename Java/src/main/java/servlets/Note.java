package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by MInol on 20.05.2017.
 */
@WebServlet("/Note")
public class Note extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:comp/env");
            String dataResourceName = "jdbc/CatalogDB";
            DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);
            Connection conn = dataSource.getConnection();
            DBManager dbManager = new DBManager();

            String materieToInsert = request.getParameter("materieStudent");
            Student studentToInsert = new Student(Integer.parseInt(request.getParameter("idStudent")), request.getParameter("numeStudent"),request.getParameter("prenumeStudent"),Integer.parseInt(request.getParameter("grupaStudent")));
            Nota notaToInsert = new Nota(Integer.parseInt(request.getParameter("notaStudent")));
            dbManager.insertStudentInscrisMaterieNote(conn, studentToInsert, materieToInsert, notaToInsert);
            conn.close();

            RequestDispatcher view = request.getRequestDispatcher("/grades.jsp");
            view.forward(request,response);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:comp/env");
            String dataResourceName = "jdbc/CatalogDB";
            DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);
            ArrayList<StudentNotaMaterie> studenti = null;
            Connection conn = dataSource.getConnection();
            DBManager dbManager = new DBManager();
            studenti = dbManager.getStudentMaterieNote(conn);
            request.setAttribute("listaStudenti",studenti);
            conn.close();
            RequestDispatcher view = request.getRequestDispatcher("/show.jsp");
            view.forward(request,response);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
}
