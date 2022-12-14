package com.example.tedabot.repository;

import com.example.tedabot.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mansurov Abdusamad  *  05.12.2022  *  12:22   *  tedaUz
 */

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByConfirmationTrue();
    @Query(value = "SELECT * FROM review r where r.confirmation = true ORDER BY date_time DESC LIMIT 10", nativeQuery = true)
    List<Review> findAllByConfirmationTrueForUsers();
    List<Review> findAllByConfirmationFalse();
}
