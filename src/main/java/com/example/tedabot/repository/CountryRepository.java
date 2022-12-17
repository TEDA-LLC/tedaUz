package com.example.tedabot.repository;

import com.example.tedabot.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  10:08   *  tedaSystem
 */

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
