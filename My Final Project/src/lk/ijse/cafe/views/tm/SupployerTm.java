package lk.ijse.cafe.views.tm;

public class SupployerTm {
    private String supployer_id;
    private String name;
    private String address;

    public SupployerTm() {
    }

    public SupployerTm(String supployer_id, String name, String address) {
        this.supployer_id = supployer_id;
        this.name = name;
        this.address = address;
    }

    public String getSupployer_id() {
        return supployer_id;
    }

    public void setSupployer_id(String supployer_id) {
        this.supployer_id = supployer_id;
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

    @Override
    public String toString() {
        return "SupployerTm{" +
                "supployer_id='" + supployer_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
