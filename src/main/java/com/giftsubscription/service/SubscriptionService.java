package com.giftsubscription.service;

import com.giftsubscription.model.*;
import com.giftsubscription.repository.*;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final AvailableSubscriptionRepository availableSubscriptionRepository;

    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            UserRepository userRepository,
            DeliveryRepository deliveryRepository,
            AvailableSubscriptionRepository availableSubscriptionRepository
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.deliveryRepository = deliveryRepository;
        this.availableSubscriptionRepository = availableSubscriptionRepository;
    }

    public boolean createSubscription(String userMail, Subscription subscriptionData) {
        Optional<User> userOptional = userRepository.findByMail(userMail);
        if (userOptional.isEmpty()) return false;

        User user = userOptional.get();

        if (user.getBalance() < subscriptionData.getPrice()) return false;

        subscriptionData.setUser(user);
        subscriptionData.setStartDate(LocalDate.now());
        subscriptionData.setEndDate(LocalDate.now().plusMonths(1));

        user.setBalance(user.getBalance() - subscriptionData.getPrice());
        userRepository.save(user);
        subscriptionRepository.save(subscriptionData);

        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(subscriptionData.getStartDate());
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setSubscription(subscriptionData);
        deliveryRepository.save(delivery);

        return true;
    }

    public boolean buySubscription(String mail, Long availableSubscriptionId) {
        Optional<User> userOpt = userRepository.findByMail(mail);
        Optional<AvailableSubscription> availableOpt = availableSubscriptionRepository.findById(availableSubscriptionId);

        if (userOpt.isEmpty() || availableOpt.isEmpty()) return false;

        User user = userOpt.get();
        AvailableSubscription available = availableOpt.get();

        if (user.getBalance() < available.getPrice()) return false;

        // Проверка: не купил ли пользователь уже эту подписку
        boolean alreadyExists = subscriptionRepository.findByUser(user)
                .stream()
                .anyMatch(s -> s.getAvailableSubscription() != null &&
                        s.getAvailableSubscription().getId().equals(availableSubscriptionId));

        if (alreadyExists) return false;

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setAvailableSubscription(available);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setPrice(available.getPrice());
        subscription.setName(available.getName());
        subscription.setType(available.getType());

        user.setBalance(user.getBalance() - available.getPrice());
        userRepository.save(user);
        subscriptionRepository.save(subscription);

        Delivery delivery = new Delivery();
        delivery.setSubscription(subscription);
        delivery.setDeliveryDate(subscription.getStartDate());
        delivery.setStatus(DeliveryStatus.PENDING);
        deliveryRepository.save(delivery);

        return true;
    }

    public List<Subscription> getUserSubscriptions(String userMail) {
        Optional<User> userOptional = userRepository.findByMail(userMail);
        return userOptional.map(subscriptionRepository::findByUser).orElse(List.of());
    }

    public List<Subscription> getSubscriptionsByUser(String mail) {
        Optional<User> userOptional = userRepository.findByMail(mail);
        return userOptional.map(subscriptionRepository::findByUser).orElse(List.of());
    }
}
