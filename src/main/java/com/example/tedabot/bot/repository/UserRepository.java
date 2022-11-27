package com.example.tedabot.bot.repository;

import com.example.tedabot.bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:36 *
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByChatId(String chatId);

    Optional<User> findByPhone(String phone);
}
