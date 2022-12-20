package com.example.tedabot.dto;

import lombok.*;

/**
 * @author Malikov Azizjon    tedaUz    16.12.2022    15:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VacancyDTO {

    private String name, description;
    private boolean active = true;


}
