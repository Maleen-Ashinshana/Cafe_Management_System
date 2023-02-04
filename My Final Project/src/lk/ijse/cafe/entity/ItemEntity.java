package lk.ijse.cafe.entity;

public class ItemEntity implements SupperEntity{
    private String code;
    private String description;
    private double unit_price;

    public ItemEntity(String code) {
        this.code = code;
    }

    public ItemEntity() {
    }

    public ItemEntity(String code, String description, double unit_price) {
        this.code = code;
        this.description = description;
        this.unit_price = unit_price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                '}';
    }
}
