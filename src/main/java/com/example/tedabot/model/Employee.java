package com.example.tedabot.model;

import com.example.tedabot.model.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Mansurov Abdusamad  *  30.11.2022  *  10:35   *  tedaSystem
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username, fullName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phoneFirst;

    private String phoneSecond;

    private String chatId;

    @ManyToOne
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    private boolean active = true;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registeredTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<RoleType> roles;

    @Enumerated(EnumType.STRING)
    private RoleType selectedRole;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority(selectedRole.name()));
//    }

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public Employee(String Username, String password, Set<RoleType> roles) {
        this.username = Username;
        this.password = password;
        this.roles = roles;
    }

    public Employee(String username, String password, Set<RoleType> roles, RoleType selectedRole) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.selectedRole = selectedRole;
    }

    public Employee(String username, String password, Set<RoleType> roles, RoleType selectedRole, String phoneFirst) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.selectedRole = selectedRole;
        this.phoneFirst = phoneFirst;
    }

}
