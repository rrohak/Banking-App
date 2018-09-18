package rohak.app.AccCreation;

import rohak.db.JDBCHandler;

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

    public String Accounttype;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = new Account();

        account.name = req.getParameter("txtName");
        account.dob = req.getParameter("DOB");
        account.address = req.getParameter("Address");
        account.email = req.getParameter("Email");
        account.acctype = Integer.parseInt(req.getParameter("AccType"));

        if(account.getAcctype() == 1){
            Accounttype = "savings";
        }else{
            Accounttype = "checkings";
        }


        JDBCHandler handler = new JDBCHandler();
        handler.createConnection();
        boolean accCreate = handler.createAccount(account);
        handler.closeConnection();

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        if (accCreate){
            writer.print("<center><h3>Account created</h3></center>");
            RequestDispatcher dispatcher = req.getRequestDispatcher("MainMenu.html");
            dispatcher.forward(req, resp);
        }else{
            writer.print("<center><h3>Account Created Failed, please try again</h3></center>");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Account Creation.html");
            dispatcher.include(req,resp);
        }
    }
}
