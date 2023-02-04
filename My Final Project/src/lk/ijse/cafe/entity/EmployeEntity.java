package lk.ijse.cafe.entity;

public class EmployeEntity implements SupperEntity {
    private String id;
    private String name;
    private String address;
    private String email;
    private int contact_number;
    private String gender;
    private String type;
    private double salary;

    public EmployeEntity() {
    }

    public EmployeEntity(String id) {
        this.id = id;
    }

    public EmployeEntity(String id, String name, String address, String email, int contact_number, String gender, String type, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact_number = contact_number;
        this.gender = gender;
        this.type = type;
        this.salary = salary;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
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
        return "EmployeEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contact_number=" + contact_number +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", salary=" + salary +
                '}';
    }
}
