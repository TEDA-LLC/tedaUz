package com.example.tedabot.repository;

import com.example.tedabot.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  13:38   *  tedaSystem
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByActiveTrueAndReadyTrue(Pageable pageable);
    Page<Order> findAllByActiveTrueAndReadyFalse(Pageable pageable);
    Page<Order> findAllByActiveFalseAndReadyTrue(Pageable pageable);
    Page<Order> findAllByActiveFalseAndReadyFalse(Pageable pageable);


}
