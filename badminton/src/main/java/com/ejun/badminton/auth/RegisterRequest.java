package com.ejun.badminton.auth;
import com.ejun.badminton.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private String dateOfBirth;
    private Role role;
}