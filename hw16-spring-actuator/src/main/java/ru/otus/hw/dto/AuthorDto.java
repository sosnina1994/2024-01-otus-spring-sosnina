package ru.otus.hw.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@ToString
public class AuthorDto {
    @NotNull(message = "Author id is can't be null!")
    private Long id;

    @NotBlank(message = "Full name is can't be empty!")
    private String fullName;
}
