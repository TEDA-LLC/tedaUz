package com.example.tedabot.repository;

import com.example.tedabot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedaUz *  * 10:45 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByNameUzOrNameRuOrNameEn(String nameUz, String nameRu    , String nameEn);
}
