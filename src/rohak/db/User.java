package rohak.db;

public class User {
    Integer ID;
    String user;
    String pass;

    public User() {
    }

    public User(Integer ID, String user, String pass) {
        this.ID = ID;
        this.user = user;
        this.pass = pass;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
