package com.example.tedabot.repository;

import com.example.tedabot.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 16:25 *
 */
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
}
