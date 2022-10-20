package com.example.tedabot.model;

import com.example.tedabot.constant.State;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username, phone, fullName, chatId;

    @Enumerated(EnumType.STRING)
    private State state;

}
