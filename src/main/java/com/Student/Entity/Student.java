package com.Student.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Size(min = 3,message = "more then 3")
    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Size(min = 10,max = 10,message = "should be 10 digits")
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

}