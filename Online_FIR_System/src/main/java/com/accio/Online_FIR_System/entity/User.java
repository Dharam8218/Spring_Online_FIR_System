package com.accio.Online_FIR_System.entity;

import com.accio.Online_FIR_System.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    private String userName;
    private String password;
    private String roles;
    private String email;
    private long mobileNo;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "filedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complain> complaints = new ArrayList<>();


}
