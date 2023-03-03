package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Username must be not empty")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @NotBlank(message = "Login must be not empty")
    @Size(min = 3, message = "Login must be at least 3 characters long")
    private String login;
    @NotBlank(message = "Password must be not empty")
    @Size(min = 5, message = "Password must be at least 7 characters long")
    private String password;
}
