package com.example.tedabot.repository;

import com.example.tedabot.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mansurov Abdusamad  *  12.12.2022  *  11:00   *  tedaSystem
 */

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
