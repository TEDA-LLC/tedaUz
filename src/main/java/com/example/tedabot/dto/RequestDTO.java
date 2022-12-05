package com.example.tedabot.dto;

import lombok.*;

/**
 * @author Mansurov Abdusamad  *  26.11.2022  *  17:03   *  tedaUz
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestDTO {

    private String name, email, phone;

    private String aboutProduct, category;
}
