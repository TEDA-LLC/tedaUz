package com.example.tedabot.bot.repository;

import com.example.tedabot.bot.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 16:25 *
 */
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
}
