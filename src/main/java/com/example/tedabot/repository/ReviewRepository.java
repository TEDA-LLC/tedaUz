package com.example.tedabot.repository;

import com.example.tedabot.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mansurov Abdusamad  *  05.12.2022  *  12:22   *  tedaUz
 */

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByConfirmationTrue();
    List<Review> findAllByConfirmationTrue(Sort sort, Pageable pageable);
    List<Review> findAllByConfirmationFalse();
}
