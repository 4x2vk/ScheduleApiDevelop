package org.example.schedule.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserSaveRequest {

    @NotBlank(message = "username is required")
    @Size(min = 4, message = "username at least 4 symbols")
    private String username;

    @NotBlank(message = "email required")
    @Email(message = "input valid email")
    private String email;

    @NotBlank(message = "password required")
    @Size(min = 6, message = "password at least 6 symbols")
    private String password;
}
