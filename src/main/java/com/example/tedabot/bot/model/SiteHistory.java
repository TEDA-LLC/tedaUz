package com.example.tedabot.bot.model;

import lombok.*;

import javax.persistence.*;

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

    private String ipAddress, email, about;

}
