package model.entities;

/**
 * Created by Dyvak on 16.12.2016.
 */
public class Product {

    private int id;
    private String name;
    private String description;
    private long price;

    public static class Builder {
        Product instance = new Product();

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            instance.description = description;
            return this;
        }

        public Builder setPrice(long price) {
            instance.price = price;
            return this;
        }

        public Product build() {
            return instance;
        }


    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public long getPrice() {
        return price;
    }

    public double getRealPrice(){
        return (double) price/100;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
