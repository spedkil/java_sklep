package com.example.spring_shop.item;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
@Table
public class Item {
    @Id
    @SequenceGenerator(
            name="shop_sequence",
            sequenceName="shop_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shop_sequence"
    )
    private long id;
    private String name;
    private float price;

    public Item() {
    }

    public Item(long id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
