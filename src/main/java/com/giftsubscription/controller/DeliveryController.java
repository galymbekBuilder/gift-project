package com.giftsubscription.controller;

import com.giftsubscription.model.Delivery;
import com.giftsubscription.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/user/{mail}")
    public ResponseEntity<List<Delivery>> getUserDeliveries(@PathVariable String mail) {
        List<Delivery> deliveries = deliveryService.getDeliveriesByUserMail(mail);
        return ResponseEntity.ok(deliveries);
    }
}

