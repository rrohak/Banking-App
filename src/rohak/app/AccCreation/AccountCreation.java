package rohak.app.AccCreation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet (name="AccountCreation", urlPatterns = "/AccCreate")
public class AccountCreation extends HttpServlet {

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


        Account account= new Account();
        String accounttype;

        account.name = req.getParameter("txtName");
        account.dob = req.getParameter("DOB");
        account.address = req.getParameter("Address");
        account.email = req.getParameter("Email");
        account.acctype = Integer.parseInt(req.getParameter("AccType"));

        if(account.getAcctype() == 1){
            accounttype = "savings";
        }else{
            accounttype = "checkings";
        }
        try {
            String sql = "insert into account_details (name, DOB, Address, Email, AccType) VALUES (?,?,?,?,?)";
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, account.name);
            pStmt.setString(2,account.dob);
            pStmt.setString(3, account.address);
            pStmt.setString(4, account.email);
            pStmt.setString(5, accounttype);

            int success = pStmt.executeUpdate();


            if(success == 1){
                PrintWriter writer = resp.getWriter();
                writer.print("Account created.");

                RequestDispatcher dispatcher = req.getRequestDispatcher("MainMenu.html");
                dispatcher.include(req,resp);
            }else{
                PrintWriter writer = resp.getWriter();
                writer.print("Try again");

                RequestDispatcher dispatcher = req.getRequestDispatcher("Account Creation.html");
                dispatcher.include(req, resp);
            }
        } catch (Exception e) {
            System.out.println("An exception has occurred: " + e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                System.out.println("It's working");
                if (con != null)
                    con.close();
                if (pStmt != null) ;
                pStmt.close();
                System.out.println("Connection has been closed");
            } catch (Exception e) {
                System.out.println("An exception has occurred: " + e);
            }
        }
    }
}
