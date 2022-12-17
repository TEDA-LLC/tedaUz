package com.example.tedabot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  10:23   *  tedaSystem
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User client;

    @ManyToOne
    private Employee employee;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime = LocalDateTime.now();

    private LocalDate nextConnectionDate;

    private String description;

    @OneToMany
    @ToString.Exclude
    private List<ReviewCategory> ReviewCategory;

    boolean success;

}
