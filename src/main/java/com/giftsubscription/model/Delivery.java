package com.giftsubscription.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    public Delivery() {
    }

    public Delivery(LocalDate deliveryDate, DeliveryStatus status, Subscription subscription) {
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.subscription = subscription;
    }

    // геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
