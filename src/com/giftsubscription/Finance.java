package com.giftsubscription;

public class Finance {
    public void addBalance(User user, double amount) {
        user.setBalance(user.getBalance() + amount);
    }
}
