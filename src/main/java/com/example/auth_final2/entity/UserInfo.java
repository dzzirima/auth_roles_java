package com.example.auth_final2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @SequenceGenerator(
            name = "user_info_sequence" ,
            allocationSize = 1,
            sequenceName = "user_info_sequence"
    )
    @GeneratedValue(
            generator = "user_info_sequence",
            strategy = GenerationType.SEQUENCE

    )
    private Long id;
    private String name;
    private String email;
    private String password;
    private String roles;
}
