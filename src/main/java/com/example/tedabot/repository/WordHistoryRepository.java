package com.example.tedabot.repository;

import com.example.tedabot.model.WordsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:29 *
 */
@Repository
public interface WordHistoryRepository extends JpaRepository<WordsHistory,Long> {
}
