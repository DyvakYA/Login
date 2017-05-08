package model.entities;

import java.util.Date;

public class Order {

    private Integer orderId;
    private String orderStatus;
    private Date date;

    public static class Builder {
        Order instance = new Order();

        public Builder setOrderId(Integer id) {
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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

        Order order = (Order) o;

        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        return orderStatus != null ? orderStatus.equals(order.orderStatus) : order.orderStatus == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
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
