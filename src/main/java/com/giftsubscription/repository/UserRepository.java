package com.giftsubscription.repository;

import com.giftsubscription.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMail(String mail);

    boolean existsByMail(String mail);

    boolean existsByNumber(String number);
}
