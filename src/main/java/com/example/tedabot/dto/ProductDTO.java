package com.example.tedabot.dto;

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
    private String nameUz, nameRu, nameEn, descriptionUz, descriptionRu, descriptionEn;

    private Long categoryId;

    private MultipartFile attachment;

    private Double price;
}
