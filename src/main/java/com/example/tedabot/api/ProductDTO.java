package com.example.tedabot.api;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:25 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private String nameUz , nameRu , descriptionUz ,descriptionRu;

    private Long categoryId;

    private MultipartFile photo;

    private Double price;
}
