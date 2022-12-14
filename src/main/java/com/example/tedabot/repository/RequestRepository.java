package com.example.tedabot.repository;

import com.example.tedabot.model.Request;
import com.example.tedabot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mansurov Abdusamad  *  24.11.2022  *  10:30   *  tedaUz
 */

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByUser(User user, Sort sort);
    List<Request> findAllByUser(User user);
    Page<Request> findAllByView(boolean view, Pageable pageable);
}
