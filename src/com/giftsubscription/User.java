package com.giftsubscription;

public class User {
    private String name;
    private String mail;
    private String number;
    private String password;
    private double balance;

    public User(String name, String mail, String number, String password) {
        this.name = name;
        this.mail = mail;
        this.number = number;
        this.password = password;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    @Override
    public String toString() {
        return "имя: " + name +
                "\nпочта: " + mail +
                "\nномер: " + number +
                "\nпароль: " + password +
                "\nбаланс: " + balance;
    }
}
