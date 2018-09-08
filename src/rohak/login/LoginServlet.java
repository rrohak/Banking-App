package rohak.login;

import rohak.db.JDBCHandler;
import rohak.db.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();

        String username = req.getParameter("txtUser");
        String password = req.getParameter("txtPass");

        user.setUser(username);
        user.setPass(password);

        JDBCHandler handler = new JDBCHandler();
        handler.createConnection();
        boolean authentication = handler.authenticateUser(user);
        handler.closeConnection();

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        if (authentication) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("MainMenu.html");
            dispatcher.forward(req,resp);
        } else {
            writer.print("<center><h3>Invalid User ID/Password, please try again</h3></center>");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Login.html");
            dispatcher.include(req,resp);
        }
    }
}
