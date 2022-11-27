package com.example.tedabot.bot.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 16:24 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Attachment photo;

}
