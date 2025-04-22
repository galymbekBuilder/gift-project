package com.giftsubscription.controller;

import com.giftsubscription.model.Subscription;
import com.giftsubscription.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSubscription(@RequestParam String mail,
                                                     @RequestBody Subscription subscriptionData) {
        boolean success = subscriptionService.createSubscription(mail, subscriptionData);
        if (success) {
            return ResponseEntity.ok("Подписка успешно оформлена.");
        } else {
            return ResponseEntity.badRequest().body("Недостаточно средств или пользователь не найден.");
        }
    }

    @GetMapping("/user/{mail}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable String mail) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUser(mail);
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buySubscription(@RequestParam String mail,
                                                  @RequestParam Long subscriptionId) {
        boolean success = subscriptionService.buySubscription(mail, subscriptionId);
        if (success) {
            return ResponseEntity.ok("Подписка успешно приобретена.");
        } else {
            return ResponseEntity.badRequest().body("Ошибка: либо недостаточно средств, либо подписка уже куплена.");
        }
    }


}
