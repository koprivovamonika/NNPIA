package com.example.nnpiacv06v02.user;


import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, max = 30)
    private String userName;

    @NotNull
    private String displayName;

    @NotNull
    @Pattern(regexp="")
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^(\\+420)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$", message = "Phone number is bad.")
    private String phone;

    public User() {}
}
