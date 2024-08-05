package ru.otus.hw.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserDto {

    @NotNull
    private Long id;

    @NotBlank(message = "User username is can not be empty")
    private String username;

    @NotBlank(message = "User password is can not be empty")
    private String password;

    private Boolean enabled;
}