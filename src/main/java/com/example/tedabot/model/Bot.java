package com.example.tedabot.model;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String token, username;
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "bot")
    @ToString.Exclude
    private List<User> userList;
}
