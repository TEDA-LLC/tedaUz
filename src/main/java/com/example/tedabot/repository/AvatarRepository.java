package com.example.tedabot.repository;

import com.example.tedabot.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mansurov Abdusamad  *  07.12.2022  *  12:12   *  tedaSystem
 */

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
