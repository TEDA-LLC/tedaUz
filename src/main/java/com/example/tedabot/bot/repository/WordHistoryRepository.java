package com.example.tedabot.bot.repository;

import com.example.tedabot.bot.model.WordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:29 *
 */
@Repository
public interface WordHistoryRepository extends JpaRepository<WordHistory,Long> {
}
