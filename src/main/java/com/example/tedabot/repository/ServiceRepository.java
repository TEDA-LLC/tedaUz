package com.example.tedabot.repository;

import com.example.tedabot.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 16:24 *
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
}
