package com.securityTest.loginApplication.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity

@Table(name = "users") // as user is reserved name in postgres
public class UserEntity {

    @Id
    @GeneratedValue
    private int id;

    private String userName;

    private String passwored;
}
