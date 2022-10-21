package com.example.tedabot.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:40 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameUz , nameRu , description;

    @ManyToOne
    private Category category;

}
