package com.example.sklep_java.Item;


public class Item {
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
