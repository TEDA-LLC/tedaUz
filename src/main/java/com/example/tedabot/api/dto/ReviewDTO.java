package com.example.tedabot.api.dto;

import lombok.*;

/**
 * @author Mansurov Abdusamad  *  05.12.2022  *  16:31   *  tedaUz
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {

    String phone, email, text;

}
