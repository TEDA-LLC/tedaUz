package com.example.tedabot.model;

import com.example.tedabot.model.enums.RegisteredType;
import com.example.tedabot.model.enums.RequestType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String aboutProduct, category;

    private boolean view = false;
    @Enumerated(EnumType.STRING)
    private RequestType requestStatusType;
    @ManyToOne
    private User user;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private RegisteredType requestType;
}
