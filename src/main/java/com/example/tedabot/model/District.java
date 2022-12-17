package com.example.tedabot.model;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class District { //TODO  tuman
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @UpdateTimestamp
    private LocalDate changeDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Region region;

    public District(String name, LocalDate changeDate, Region region) {
        this.name = name;
        this.changeDate = changeDate;
        this.region = region;
    }

    public District(String name, Region region) {
        this.name = name;
        this.region = region;
    }
}
