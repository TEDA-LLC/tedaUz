package com.example.tedabot.repository;

import com.example.tedabot.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  13:37   *  tedaSystem
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByPhoneFirst(String phoneFirst);
    Page<Employee> findAllByActiveTrue(Pageable pageable);
    Page<Employee> findAllByActiveFalse(Pageable pageable);
    Page<Employee> findAllByActiveTrueAndCompany_Id(Long companyId, Pageable pageable);
    Page<Employee> findAllByActiveFalseAndCompany_Id(Long companyId, Pageable pageable);

    Page<Employee> findAllByCompany_Id(Long companyId, Pageable pageable);

}
