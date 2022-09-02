package com.api.coding_challenge.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(length = 128)
    private String firstName;

    @Column(length = 128)
    private String lastName;

    private LocalDate dateOfBirth;

    @Transient
    private Long age;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
