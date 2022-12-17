package com.example.tedabot.model;

import com.example.tedabot.model.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  10:59   *  tedaSystem
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User client;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Employee receiver;
    @ManyToOne
    private Product product;
    private Double price;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordedTime = LocalDateTime.now();
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<Payment> payments;
    private LocalDate startDate;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private LocalDate endTime;
    private boolean ready = false;
    private boolean active = true;

}
