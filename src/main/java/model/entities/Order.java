package model.entities;

import java.util.Date;

public class Order implements Identified {

    private int id;
    private String orderStatus;
    private Date date;
    private long totalPrice;

    public static class Builder {
        Order instance=new Order();

        public Builder setOrderId(int id) {
            instance.id=id;
            return this;
        }

        public Builder setOrderStatus(String status) {
            instance.orderStatus=status;
            return this;
        }

        public Builder setDate(Date date) {
            instance.date=date;
            return this;
        }

        public Builder setTotalPrice(long totalPrice) {
            instance.totalPrice=totalPrice;
            return this;
        }

        public Order build() {
            return instance;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus=orderStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date=date;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public double getRealTotalPrice() {
        return totalPrice / 100;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice=totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order=(Order) o;

        if (id != order.id) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        return date != null ? date.equals(order.date) : order.date == null;
    }

    @Override
    public int hashCode() {
        int result=id;
        result=31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result=31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus='" + orderStatus + '\'' +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
