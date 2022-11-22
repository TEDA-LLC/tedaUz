package com.example.tedabot.bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameUz, nameRu, nameEn, descriptionUz, descriptionRu, descriptionEn;

    @ManyToOne
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Attachment attachment;

    private Double price;

}
