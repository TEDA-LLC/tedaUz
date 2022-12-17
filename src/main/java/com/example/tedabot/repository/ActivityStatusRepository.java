package com.example.tedabot.repository;

import com.example.tedabot.model.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mansurov Abdusamad  *  15.12.2022  *  10:50   *  tedaSystem
 */

@Repository
public interface ActivityStatusRepository extends JpaRepository<ActivityStatus, Long> {

    @Query(value = "SELECT * FROM activity_status s where s.id = ?1  ORDER BY period DESC LIMIT 1", nativeQuery = true)
    Optional<ActivityStatus> getLastActivity(Long id);

    @Query(value = "SELECT * FROM activity_status s where s.client_id = ?1  ORDER BY period DESC", nativeQuery = true)
    List<ActivityStatus> getByUser(Long id);

}
