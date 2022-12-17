package com.example.tedabot.repository;

import com.example.tedabot.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  13:38   *  tedaSystem
 */

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
