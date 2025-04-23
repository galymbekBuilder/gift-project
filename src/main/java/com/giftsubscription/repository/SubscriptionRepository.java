package com.giftsubscription.repository;

import com.giftsubscription.model.Subscription;
import com.giftsubscription.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUser(User user);
    boolean existsByUser(User user);
}



