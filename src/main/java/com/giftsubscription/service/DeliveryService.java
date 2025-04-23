package com.giftsubscription.service;

import com.giftsubscription.model.Delivery;
import com.giftsubscription.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getDeliveriesByUserMail(String mail) {
        return deliveryRepository.findBySubscription_User_Mail(mail);
    }

}

