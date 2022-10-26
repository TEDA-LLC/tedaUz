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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameUz , nameRu , descriptionUz ,descriptionRu;

    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment attachment;

    private Double price;

}
