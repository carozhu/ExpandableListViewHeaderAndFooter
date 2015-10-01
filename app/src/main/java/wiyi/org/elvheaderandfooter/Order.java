package wiyi.org.elvheaderandfooter;

import java.util.List;

/**
 * Created by xing on 9/25/15.
 */
public class Order {
    private String orderName ;
    private List<OrderItem> items ;
    private double totalPrices ;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalPrices() {
        double prices = 0 ;
        for (int i=0;i<items.size();i++) {
            prices += items.get(i).getPrice() ;
        }
        totalPrices = prices ;

        return totalPrices;
    }

    public void setTotalPrices(double totalPrices) {
        this.totalPrices = totalPrices;
    }
}
