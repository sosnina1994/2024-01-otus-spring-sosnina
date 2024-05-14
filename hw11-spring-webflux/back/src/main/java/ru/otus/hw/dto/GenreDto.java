package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class GenreDto {
    @NotBlank(message = "Genre id is can't be null!")
    private String id;

    @NotBlank(message = "Genre name is can't be empty!")
    private String name;
}
