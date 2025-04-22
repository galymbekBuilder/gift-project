package com.giftsubscription.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    private String name;

    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    private LocalDate startDate; // Когда началась
    private LocalDate endDate;   // Когда закончится

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "available_subscription_id")
    private AvailableSubscription availableSubscription;

    public Subscription() {
    }

    public Subscription(LocalDate startDate, LocalDate endDate, User user, AvailableSubscription availableSubscription) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.availableSubscription = availableSubscription;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AvailableSubscription getAvailableSubscription() {
        return availableSubscription;
    }

    public void setAvailableSubscription(AvailableSubscription availableSubscription) {
        this.availableSubscription = availableSubscription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }



}
