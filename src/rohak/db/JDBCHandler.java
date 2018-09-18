package rohak.db;

import rohak.app.AccCreation.Account;
import rohak.app.AccCreation.AccountCreation;

import javax.xml.transform.Result;
import java.sql.*;

public class JDBCHandler {
    Connection con;

    Statement stmt;
    PreparedStatement pStmt;

    public JDBCHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (Exception e) {
            System.out.println("An exception has occurred: " + e);
        }
    }

    public void createConnection() {
        try {
            String user = "root";
            String password = "doraemon1";
            String url = "jdbc:mysql://localhost:3306/Final_Project?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println("An exception has occurred: " + e);
        }
    }

    public boolean authenticateUser(User user) {
        boolean auth = false;

        try {
            String sql = "select * from user_pass where username = ? and password = ?";

            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, user.user);
            pStmt.setString(2, user.pass);

            ResultSet resultset = pStmt.executeQuery();

            auth = resultset.next();
            if (auth == true) {
                System.out.println("User has been authenticated");
            } else {
                System.out.println("Authentication has failed");
            }
        } catch (Exception e) {
            System.out.println("An exception has occurred: " + e);
        }
        return auth;
    }

    public boolean createAccount(Account account){
        boolean acc = false;

        AccountCreation accountCreation = new AccountCreation();

        try {
            String sql = "insert into account_details (name, DOB, Address, Email, AccType) VALUES (?,?,?,?,?)";
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, account.name);
            pStmt.setString(2,account.dob);
            pStmt.setString(3, account.address);
            pStmt.setString(4, account.email);
            pStmt.setString(5, accountCreation.Accounttype);

            acc = pStmt.execute();

            if (acc != true){
                System.out.println("Account has been created");
            }else{
                System.out.println("Account creation failed, please try again");
            }
        }catch (Exception e){
            System.out.println("An exception has occurred: " + e);
        }

        return !acc;
    }

    public void closeConnection() {
        try {
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
            if (pStmt != null) ;
                pStmt.close();
            System.out.println("Connection has been closed");
        }catch (Exception e){
            System.out.println("An exception has occurred: "+e);
        }
    }
}
