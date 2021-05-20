package com.filos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequests {
    @Email
    private String email;
    @NotBlank
    private String password;
}
