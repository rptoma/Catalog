package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    void showAlert(PrintWriter out, String eroare){
        out.println("<script type=\"text/javascript\">");
        out.println("alert('"+eroare+"');");
        out.println("</script>");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:comp/env");
            String dataResourceName = "jdbc/CatalogDB";
            DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);
            Connection conn = dataSource.getConnection();
            DBManager dbManager = new DBManager();


            if (request.getParameter("buttonAfiseaza") != null) {
                try {
                    ArrayList<StudentNotaMaterie> studenti = null;
                    studenti = dbManager.getStudentMaterieNote(conn);
                    request.setAttribute("listaStudenti", studenti);
                    conn.close();
                    RequestDispatcher view = request.getRequestDispatcher("/show.jsp");
                    view.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            } else if (request.getParameter("buttonInsereaza") != null) {
                try {
                    PrintWriter out = response.getWriter();


                    String materieToInsert = request.getParameter("materieStudent");
                    Integer idToAdd = Integer.parseInt(request.getParameter("idStudent"));
                    String numeToAdd = request.getParameter("numeStudent");
                    String prenumeToAdd = request.getParameter("prenumeStudent");
                    Integer grupaToAdd = Integer.parseInt(request.getParameter("grupaStudent"));
                    Integer notaToAdd = Integer.parseInt(request.getParameter("notaStudent"));


                    Student studentToInsert = new Student(idToAdd,numeToAdd,prenumeToAdd,grupaToAdd);
                    Nota notaToInsert = new Nota(notaToAdd);

                    try {
                        dbManager.insertStudentInscrisMaterieNote(conn, studentToInsert, materieToInsert, notaToInsert);
                    } catch (SQLException e) {
                        showAlert(out, "Nu s-a putut face inserarea");
                    }


                    conn.close();

                } catch (Exception e) {
                    e.printStackTrace(System.out);

                }
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
