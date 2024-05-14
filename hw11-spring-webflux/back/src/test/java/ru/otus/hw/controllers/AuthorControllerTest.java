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
import ru.otus.hw.dto.AuthorDto;
import ru.otus.hw.mappers.AuthorMapper;
import ru.otus.hw.models.Author;
import ru.otus.hw.repositories.AuthorRepository;

import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Тестирование контроллера авторов")
@ExtendWith(SpringExtension.class)
@Import(AuthorMapper.class)
@WebFluxTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;

    @DisplayName("Получение списка авторов")
    @Test
    void shouldGetAllAuthors() {
        Author[] exampleList = getExampleAuthors();
        given(authorRepository.findAll()).willReturn(Flux.just(exampleList));

        var result = webTestClient.get().uri("/api/authors")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(AuthorDto.class)
                .getResponseBody();
        var step = StepVerifier.create(result);
        StepVerifier.Step<AuthorDto> stepResult = null;
        for (Author author : exampleList) {
            stepResult = step.expectNext(mapper.toDto(author));
        }

        assertThat(stepResult).isNotNull();
        stepResult.verifyComplete();
    }

    private Author[] getExampleAuthors() {
        return LongStream.range(1L, 4L).boxed()
                .map(id -> new Author(String.valueOf(id), "name %d".formatted(id)))
                .toArray(Author[]::new);
    }
}