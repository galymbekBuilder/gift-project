package com.giftsubscription.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "users") // имя таблицы в БД
public class User {
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "Имя не должно быть пустым")
    @Column(nullable = false)
    private String name;

    @Email(message = "Неверный формат электронной почты")
    @NotBlank(message = "Почта обязательна")
    @Column(unique = true, nullable = false)
    private String mail;

    @NotBlank(message = "Номер телефона обязателен")
    @Pattern(regexp = "\\+?\\d{10,15}", message = "Номер должен содержать от 10 до 15 цифр")
    @Column(unique = true, nullable = false)
    private String number;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен быть не короче 6 символов")
    @Column(nullable = false)
    private String password;


    private double balance;

    // Конструктор без аргументов для JPA
    public User() {
    }

    // Конструктор для ручного создания
    public User(String name, String mail, String number, String password) {
        this.name = name;
        this.mail = mail;
        this.number = number;
        this.password = password;
        this.balance = 0;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Override
    public String toString() {
        return "имя: " + name +
                "\nпочта: " + mail +
                "\nномер: " + number +
                "\nпароль: " + password +
                "\nбаланс: " + balance;
    }
}

