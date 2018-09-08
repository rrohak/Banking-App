package rohak.app.AccCreation;

public class Account {
    String name;
    String dob;
    String address;
    String email;
    Integer acctype;

    public Account() {
    }

    public Account(String name, String date, String address, String email, Integer acctype) {
        this.name = name;
        this.dob = date;
        this.address = address;
        this.email = email;
        this.acctype = acctype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return dob;
    }

    public void setDate(String date) {
        this.dob = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAcctype() {
        return acctype;
    }

    public void setAcctype(Integer acctype) {
        this.acctype = acctype;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", date='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", acctype='" + acctype + '\'' +
                '}';
    }
}
