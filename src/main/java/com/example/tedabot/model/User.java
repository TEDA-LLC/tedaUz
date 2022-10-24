package com.example.tedabot.model;

import com.example.tedabot.constant.enums.Language;
import com.example.tedabot.constant.enums.State;
import lombok.*;

import javax.persistence.*;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 16:03 *
 */
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username, phone, fullName;

    @Column(unique = true)
    private String chatId;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Language language;
}
