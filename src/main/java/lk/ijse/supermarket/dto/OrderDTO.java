package lk.ijse.supermarket.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private int id;
    private int customerId;
    private Date date;
    private List<OrderItemDTO> orderItems;

    public OrderDTO() {
    }

    public OrderDTO(int customerId, Date date, List<OrderItemDTO> orderItems) {
        this.customerId = customerId;
        this.date = date;
        this.orderItems = orderItems;
    }

    public OrderDTO(int id, int customerId, Date date, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "id=" + id + ", customerId=" + customerId + ", date=" + date + ", orderItems=" + orderItems + '}';
    }
}
