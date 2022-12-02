package com.example.tedabot.bot.model;

import com.example.tedabot.bot.model.enums.RegistredType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Mansurov Abdusamad  *  24.11.2022  *  10:22   *  tedaUz
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aboutProduct, category;

    private boolean view = false;

    @ManyToOne
    private User user;

    private LocalDateTime dateTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private RegistredType requestType;
}
