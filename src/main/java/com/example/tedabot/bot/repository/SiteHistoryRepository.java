package com.example.tedabot.bot.repository;

import com.example.tedabot.bot.model.SiteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 15:33 *
 */
@Repository
public interface SiteHistoryRepository extends JpaRepository<SiteHistory,Long> {
}
