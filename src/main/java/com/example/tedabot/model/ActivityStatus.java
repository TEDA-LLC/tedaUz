package com.example.tedabot.model;

import com.example.tedabot.model.enums.ActiveTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Mansurov Abdusamad  *  15.12.2022  *  10:29   *  tedaSystem
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ActivityStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ActiveTypes firstCase;
    private ActiveTypes secondCase;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime period = LocalDateTime.now();
    @ManyToOne
    private User client;
}
