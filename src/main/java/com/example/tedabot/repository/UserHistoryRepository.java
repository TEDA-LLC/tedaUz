package com.example.tedabot.repository;

import com.example.tedabot.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 11:50 *
 */
@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    @Query(value = "select count(id) as amount from user_history where product_id =:id",nativeQuery = true)
    long getAmountByProduct(@Param("id") long id);
}
