package com.example.tedabot.bot.model;

import com.example.tedabot.bot.constant.enums.Language;
import com.example.tedabot.bot.constant.enums.RegistrationType;
import com.example.tedabot.bot.constant.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 16:03 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username, phone, fullName;

    @Column(unique = true)
    private String chatId;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private State state;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Enumerated(EnumType.STRING)
    private RegistrationType registrationType;

    private LocalDateTime registeredTime,lastOperationTime;
}