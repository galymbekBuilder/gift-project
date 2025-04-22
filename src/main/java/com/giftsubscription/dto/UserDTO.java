package com.giftsubscription.dto;

import com.giftsubscription.model.SubscriptionType;
import jakarta.validation.constraints.*;

public class UserDTO {
    @NotBlank(message = "Имя обязательно")
    private String name;

    @Email(message = "Невалидный email")
    @NotBlank(message = "Email обязателен")
    private String email;

    @NotNull(message = "Тип подписки обязателен")
    private SubscriptionType subscriptionType;

    // геттеры и сеттеры

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
