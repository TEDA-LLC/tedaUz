package com.example.tedabot.repository;

import com.example.tedabot.model.ReviewCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  13:39   *  tedaSystem
 */
@Repository
public interface ReviewCategoryRepository extends JpaRepository<ReviewCategory, Long> {
    List<ReviewCategory> findAllByActiveTrue();
    Optional<ReviewCategory> findByName(String name);
}
