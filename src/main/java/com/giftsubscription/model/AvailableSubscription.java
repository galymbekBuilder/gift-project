package com.giftsubscription.model;

import jakarta.persistence.*;

@Entity
public class AvailableSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    private double price;

    private String bonuses; // опционально

    // геттеры/сеттеры/конструкторы
    public AvailableSubscription() {}

    public AvailableSubscription(String name, String description, SubscriptionType type, double price, String bonuses) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.bonuses = bonuses;
    }

    // геттеры и сеттеры ниже
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public SubscriptionType getType() { return type; }
    public void setType(SubscriptionType type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getBonuses() { return bonuses; }
    public void setBonuses(String bonuses) { this.bonuses = bonuses; }
}
