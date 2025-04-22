package com.giftsubscription.repository;

import com.giftsubscription.model.AvailableSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailableSubscriptionRepository extends JpaRepository<AvailableSubscription, Long> {
    Optional<AvailableSubscription> findByName(String name);
}
