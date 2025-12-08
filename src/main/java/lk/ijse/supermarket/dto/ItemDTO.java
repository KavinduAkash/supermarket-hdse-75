package lk.ijse.supermarket.dto;

public class ItemDTO {

    private int id;
    private String name;
    private int qty;
    private double unitPrice;

    public ItemDTO() {
    }

    public ItemDTO(String name, int qty, double unitPrice) {
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
    
    public ItemDTO(int id, String name, int qty, double unitPrice) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemDTO{" + "id=" + id + ", name=" + name + ", qty=" + qty + ", unitPrice=" + unitPrice + '}';
    }
}
