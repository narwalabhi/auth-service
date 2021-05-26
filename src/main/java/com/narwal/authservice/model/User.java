package com.narwal.authservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document("users")
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String mobileNumber;
    private String password;
    private String roles;
    private String gender;
}
