package wiyi.org.elvheaderandfooter;

/**
 * Created by xing on 9/25/15.
 */
public class OrderItem {
    private String name ;
    private double price ;
    private int quantity ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
