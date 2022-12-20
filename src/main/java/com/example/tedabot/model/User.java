package com.example.tedabot.model;

import com.example.tedabot.model.enums.Gender;
import com.example.tedabot.model.enums.Language;
import com.example.tedabot.model.enums.RegisteredType;
import com.example.tedabot.model.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  10:08   *  tedaSystem
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username, fullName;
    @Column(unique = true)
    private String phone, email;
    @Column(unique = true)
    private String chatId;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private State state;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String passportNumber;
    @Enumerated(EnumType.STRING)
    private Language language;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(mappedBy = "user")
    private Avatar avatar;
    private int count = 0;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registeredTime = LocalDateTime.now();
    private LocalDateTime lastOperationTime;
    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<ActivityStatus> statusList;
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private RegisteredType registeredType;
}
