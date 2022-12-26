package com.example.tedabot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    @ManyToOne
    private Address address;
    @ManyToOne
    private Employee director;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registeredTime = LocalDateTime.now();
    @ManyToMany
    @ToString.Exclude
    private List<User> clientList;
    @ManyToMany
    @ToString.Exclude
    private List<Employee> employees;
    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Bot> botList;
    @Builder.Default
    private boolean active = true;

    public Company(Employee director) {
        this.director = director;
    }

    public Company(String name, String phone, Address address, List<User> employees, Employee director) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.director = director;
    }
}