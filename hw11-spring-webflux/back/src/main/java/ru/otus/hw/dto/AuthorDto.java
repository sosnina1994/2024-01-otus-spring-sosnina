package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AuthorDto {
    @NotBlank(message = "Author id is can't be null!")
    private String id;

    @NotBlank(message = "Full name is can't be empty!")
    private String fullName;
}
