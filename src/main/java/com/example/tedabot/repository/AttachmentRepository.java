package com.example.tedabot.repository;

import com.example.tedabot.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedaUz *  * 10:48 *
 */
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
}
