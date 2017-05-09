package model.entities;

import java.util.Date;

public class Order {

    private int orderId;
    private String orderStatus;
    private Date date;

    public static class Builder {
        Order instance = new Order();

        public Builder setOrderId(int id) {
            instance.orderId = id;
            return this;
        }

        public Builder setOrderStatus(String status) {
            instance.orderStatus = status;
            return this;
        }

        public Builder setDate(Date date) {
            instance.date=date;
            return this;
        }

        public Order build() {
            return instance;
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date=date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order=(Order) o;

        if (orderId != order.orderId) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        return date != null ? date.equals(order.date) : order.date == null;
    }

    @Override
    public int hashCode() {
        int result=orderId;
        result=31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result=31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderStatus='" + orderStatus + '\'' +
                ", date=" + date +
                '}';
    }
}
