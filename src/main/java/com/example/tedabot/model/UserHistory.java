package com.example.tedabot.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 11:45 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp timeStamp;

    @ManyToOne
    private Product product;
}
