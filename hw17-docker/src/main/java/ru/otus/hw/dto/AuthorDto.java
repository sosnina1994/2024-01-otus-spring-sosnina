package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AuthorDto {
    @NotNull(message = "Author id is can't be null!")
    private Long id;

    @NotBlank(message = "Full name is can't be empty!")
    private String fullName;
}
