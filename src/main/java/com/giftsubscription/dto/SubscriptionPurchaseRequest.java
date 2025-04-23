package com.giftsubscription.dto;

import jakarta.validation.constraints.*;

public class SubscriptionPurchaseRequest {

    @NotNull
    private Long subscriptionId;

    @Email
    @NotBlank
    private String mail;

    // геттеры и сеттеры

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
