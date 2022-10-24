package com.example.tedabot.repository;

import com.example.tedabot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 16:23 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
