package ru.netology.domain;

public class TShirt extends Product {
    private String producer;

    public TShirt() {
    }

    public TShirt(int id, String name, int price, String producer) {
        super(id, name, price);
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}