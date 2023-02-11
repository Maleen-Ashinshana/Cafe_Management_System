package lk.ijse.cafe.views.tm;

public class EmployeTm {
    private String employe_id;
    private String name;
    private String address;
    private String email;
    private int contact_num;
    private String gender;
    private String type;
    private double salary;

    public EmployeTm() {
    }

    public EmployeTm(String employe_id) {
        this.employe_id = employe_id;
    }

    public EmployeTm(String employe_id, String name, String address, String email, int contact_num, String gender, String type, double salary) {
        this.employe_id = employe_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact_num = contact_num;
        this.gender = gender;
        this.type = type;
        this.salary = salary;
    }

    public String getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(String employe_id) {
        this.employe_id = employe_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact_num() {
        return contact_num;
    }

    public void setContact_num(int contact_num) {
        this.contact_num = contact_num;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeTm{" +
                "employe_id='" + employe_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contact_num=" + contact_num +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", salary=" + salary +
                '}';
    }
}
