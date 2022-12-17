package com.example.tedabot.model;

import com.example.tedabot.model.enums.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment {//TODO tolovlar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer paymentNumber;
    private LocalDate paymentDate;
    private Double price;
    private boolean active;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Employee receiver;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
