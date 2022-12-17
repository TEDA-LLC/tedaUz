package com.example.tedabot.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  10:56   *  tedaSystem
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class ReviewCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean active = true;

}
