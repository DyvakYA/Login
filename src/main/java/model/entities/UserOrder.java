package model.entities;

public class UserOrder {

    private Integer userId;
    private Integer orderId;

    public static class Builder {
        UserOrder instance = new UserOrder();

        public Builder setUserId(Integer userId) {
            instance.userId = userId;
            return this;
        }

        public Builder setOrderId(Integer orderId) {
            instance.orderId = orderId;
            return this;
        }

        public UserOrder build() {
            return instance;
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOrder)) return false;

        UserOrder that = (UserOrder) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return orderId != null ? orderId.equals(that.orderId) : that.orderId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}
