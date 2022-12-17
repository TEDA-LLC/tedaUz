package com.example.tedabot.repository;

import com.example.tedabot.model.Call;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  13:36   *  tedaSystem
 */

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {
    Page<Call> findAllByEmployee_Id(Long employee_id, Pageable pageable);
    Page<Call> findAllByEmployee_IdAndCreatedTimeBefore(Long employee_id, LocalDateTime createdTime, Pageable pageable);
    Page<Call> findAllByEmployee_IdAndCreatedTimeAfter(Long employee_id, LocalDateTime createdTime, Pageable pageable);
    Page<Call> findAllByEmployee_IdAndCreatedTimeBetween(Long employee_id, LocalDateTime createdTime, LocalDateTime createdTime2, Pageable pageable);
    Page<Call> findAllByClient_Id(Long clientId, Pageable pageable);
}
