package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class GenreDto {
    @NotNull(message = "Genre id is can't be null!")
    private Long id;

    @NotBlank(message = "Genre name is can't be empty!")
    private String name;
}
