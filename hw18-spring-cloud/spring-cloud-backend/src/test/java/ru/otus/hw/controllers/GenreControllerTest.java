package ru.otus.hw.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.dto.GenreDto;
import ru.otus.hw.services.GenreServiceImpl;

import java.util.List;
import java.util.stream.LongStream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование контроллера жанров")
@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GenreServiceImpl genreService;

    @DisplayName("Получение списка жанров")
    @Test
    void getGenres() throws Exception {
        List<GenreDto> exampleList = getExampleGenreList();

        given(genreService.findAll()).willReturn(exampleList);
        mvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(exampleList)));
    }

    private List<GenreDto> getExampleGenreList() {
        return LongStream.range(1L, 4L).boxed()
                .map(id -> new GenreDto(id, "name %d".formatted(id)))
                .toList();
    }
}