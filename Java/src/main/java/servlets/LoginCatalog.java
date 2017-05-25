package servlets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by MInol on 20.05.2017.
 */
@WebServlet("/LoginCatalog")
public class LoginCatalog extends HttpServlet {
    void showAlert(PrintWriter out,HttpServletResponse resp){
        String eroare = "Numele de utilizator este incorect sau parola este incorecta!";
        out.println("<script type=\"text/javascript\">");
        out.println("alert('"+eroare+"');");
        out.println("</script>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("pass");
        ArrayList<Materie> materii = null;
        PrintWriter out = resp.getWriter();
        try {
            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:comp/env");
            String dataResourceName = "jdbc/CatalogDB";
            DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);
            Connection conn = dataSource.getConnection();
            DBManager dbManager = new DBManager();
            materii = dbManager.getMaterii(conn);
            req.setAttribute("listaMaterii",materii);
            conn.close();
            if (username.equals("alex") && password.equals("password")) {
                RequestDispatcher view = req.getRequestDispatcher("/grades.jsp");
                view.forward(req, resp);
            } else {
                showAlert(out,resp);
            }
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }


}
