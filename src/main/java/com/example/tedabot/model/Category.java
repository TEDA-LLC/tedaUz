package com.example.tedabot.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  10:08   *  tedaSystem
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nameUz;

    @Column(unique = true)
    private String nameRu;

    @Column(unique = true)
    private String nameEn;
}
