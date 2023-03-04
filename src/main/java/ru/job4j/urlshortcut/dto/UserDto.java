package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Login must be not empty")
    @Size(min = 3, message = "Login must be at least 3 characters long")
    private String login;
    @NotBlank(message = "Password must be not empty")
    @Size(min = 5, message = "Password must be at least 7 characters long")
    private String password;
}
