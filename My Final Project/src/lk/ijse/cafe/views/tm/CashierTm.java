package lk.ijse.cafe.views.tm;

public class CashierTm {
    private String system_user_id;
    private String name;
    private String address;
    private int contact_number;
    private String type;
    private String email;
    private String password;

    public CashierTm() {
    }

    public CashierTm(String system_user_id, String name, String address, int contact_number, String type, String email, String password) {
        this.system_user_id = system_user_id;
        this.name = name;
        this.address = address;
        this.contact_number = contact_number;
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public String getSystem_user_id() {
        return system_user_id;
    }

    public void setSystem_user_id(String system_user_id) {
        this.system_user_id = system_user_id;
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

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
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

    @Override
    public String toString() {
        return "CashierTm{" +
                "system_user_id='" + system_user_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact_number=" + contact_number +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
