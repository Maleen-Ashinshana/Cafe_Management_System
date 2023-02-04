package lk.ijse.cafe.dto;

public class CustomerDTO {
    private String customer_id;
    private String name;

    public CustomerDTO() {
    }

    public CustomerDTO(String customer_id, String name) {
        this.customer_id = customer_id;
        this.name = name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customer_id='" + customer_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
