package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.otus.hw.dto.GenreDto;
import ru.otus.hw.mappers.GenreMapper;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.GenreRepository;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Тестирование контроллера жанров")
@ExtendWith(SpringExtension.class)
@Import(GenreMapper.class)
@WebFluxTest(controllers = GenreController.class)
class GenreControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper mapper;

    @DisplayName("Должен вернуть валидный список жанров")
    @Test
    void shouldGetAllGenres() {
        Genre[] exampleList = getExampleGenres();
        given(genreRepository.findAll()).willReturn(Flux.just(exampleList));

        var result = webTestClient.get().uri("/api/genres")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(GenreDto.class)
                .getResponseBody();
        var step = StepVerifier.create(result);
        StepVerifier.Step<GenreDto> stepResult = null;
        for (Genre genre : exampleList) {
            stepResult = step.expectNext(mapper.toDto(genre));
        }

        assertThat(stepResult).isNotNull();
        stepResult.verifyComplete();
    }

    private Genre[] getExampleGenres() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Genre(String.valueOf(id), "name %d".formatted(id)))
                .toArray(Genre[]::new);
    }
}