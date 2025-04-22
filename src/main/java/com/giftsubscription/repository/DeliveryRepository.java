package com.giftsubscription.repository;

import com.giftsubscription.model.Delivery;
import com.giftsubscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findBySubscription(Subscription subscription);
    List<Delivery> findBySubscription_User_Mail(String mail); // ← вот это добавь
}


