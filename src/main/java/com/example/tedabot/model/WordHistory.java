package com.example.tedabot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:27 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WordHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Timestamp timeStamp;

    private String word;
}
