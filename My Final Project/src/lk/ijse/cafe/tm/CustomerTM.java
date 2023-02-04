package lk.ijse.cafe.tm;

public class CustomerTM {
    private String id;
    private String name;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name) {
        this.id = id;
        this.name = name;
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
    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + id + '\'' +
                "name'" + name +
                '}';
    }
}
