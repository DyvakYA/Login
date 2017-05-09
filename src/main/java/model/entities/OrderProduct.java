package model.entities;

public class OrderProduct {

    private int id;
    private int orderId;
    private int productId;
    private int quantity;

    public static class Builder {
        OrderProduct instance = new OrderProduct();

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public Builder setOrderId(int orderId) {
            instance.orderId = orderId;
            return this;
        }

        public Builder setProductId(int productId) {
            instance.productId = productId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            instance.quantity = quantity;
            return this;
        }

        public OrderProduct build() {
            return instance;
        }
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProduct)) return false;

        OrderProduct that=(OrderProduct) o;

        if (id != that.id) return false;
        if (orderId != that.orderId) return false;
        if (productId != that.productId) return false;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        int result=id;
        result=31 * result + orderId;
        result=31 * result + productId;
        result=31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
