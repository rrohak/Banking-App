package rohak.login;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String user = req.getParameter("txtUser");
        String pass = req.getParameter("txtPass");

        if(!user.isEmpty() && !pass.isEmpty()){
            chain.doFilter(req, resp);
        }else{
            writer.print("<center>Please enter login details to continue</center>");

            RequestDispatcher dispatcher = req.getRequestDispatcher("Login.html");
            dispatcher.include(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
