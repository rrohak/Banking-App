package rohak.app.Transact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@WebServlet(name = "Transactions", urlPatterns = "/transact")
public class Transaction extends HttpServlet {
    Connection con;

    Statement stmt;
    PreparedStatement pStmt;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (Exception e) {
            System.out.println("An exception has occurred: " + e);
        }
        try {
            String user = "root";
            String password = "doraemon1";
            String url = "jdbc:mysql://localhost:3306/Final_Project?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println("An exception has occurred: " + e);
        }

        resp.setContentType("text/html");

        int accNumber = Integer.parseInt(req.getParameter("AccountNumber"));


    }
}
