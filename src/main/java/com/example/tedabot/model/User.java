package com.example.tedabot.model;

import com.example.tedabot.model.enums.Language;
import com.example.tedabot.model.enums.RegisteredType;
import com.example.tedabot.model.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String username, fullName;

//    @Column(nullable = false)
    private String phone, email;

    @Column(unique = true)
    private String chatId;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private State state;

    @Enumerated(EnumType.STRING)
    private Language language;

    private int count = 0;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registeredTime = LocalDateTime.now();

    private LocalDateTime lastOperationTime;

    private RegisteredType registredType;
}
