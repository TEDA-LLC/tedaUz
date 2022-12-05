package com.example.tedabot.repository;

import com.example.tedabot.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mansurov Abdusamad  *  24.11.2022  *  10:30   *  tedaUz
 */

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
