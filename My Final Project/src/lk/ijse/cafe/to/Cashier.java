package lk.ijse.cafe.to;

public class Cashier {
    private String id;
    private String name;
    private String address;
    private int con;
    private String type;
    private String email;
    private String password;

    public Cashier() {
    }

    public Cashier(String id, String name, String address, int con, String type, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.con = con;
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
