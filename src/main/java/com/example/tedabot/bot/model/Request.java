package com.example.tedabot.bot.model;

import com.example.tedabot.bot.model.enums.RequestType;
import lombok.*;

import javax.persistence.*;

/**
 * @author Mansurov Abdusamad  *  24.11.2022  *  10:22   *  tedaUz
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, phone;

    private String aboutProduct;

    private boolean view = false;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;
}
