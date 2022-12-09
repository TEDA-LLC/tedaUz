package com.example.tedabot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 15:31 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class SiteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress, about;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    private User user;
}
