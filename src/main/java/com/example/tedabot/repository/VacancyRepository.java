package com.example.tedabot.repository;

import com.example.tedabot.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malikov Azizjon    tedaUz    16.12.2022    15:56
 */
@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

//    List<Vacancy> findAllByVacancyId(Long id);

}
