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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.hw.dto.CommentCreateDto;
import ru.otus.hw.dto.CommentDto;
import ru.otus.hw.dto.CommentUpdateDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.CommentMapper;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("Тестирование контроллера комментариев")
@ExtendWith(SpringExtension.class)
@Import(CommentMapper.class)
@WebFluxTest(controllers = CommentController.class)
class CommentControllerTest {
    private static final String FIRST_BOOK_ID = "1";

    private static final String FIRST_COMMENT_ID = "1";

    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CommentMapper mapper;

    @DisplayName("Получение комментариев по идентификатору книги")
    @Test
    void getCommentsByBookId() throws Exception {
        Comment[] exampleList = getExampleComments();

        given(commentRepository.findAllByBookId(any())).willReturn(Flux.just(exampleList));

        var result = webTestClient.get()
                .uri("/api/books/%s/comments".formatted(FIRST_BOOK_ID))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(CommentDto.class)
                .getResponseBody();
        var step = StepVerifier.create(result);
        StepVerifier.Step<CommentDto> stepResult = null;
        for (Comment comment : exampleList) {
            stepResult = step.expectNext(mapper.toDto(comment));
        }

        assertThat(stepResult).isNotNull();
        stepResult.verifyComplete();
    }

    @DisplayName("Ошибка получения комментариев книги")
    @Test
    void getCommentsByBookIdWithException() throws Exception {
        given(commentRepository.findAllByBookId(any())).willThrow(NotFoundException.class);

        webTestClient.get()
                .uri("/api/books/%s/comments".formatted(FIRST_BOOK_ID))
                .exchange()
                .expectStatus().isNotFound();
    }

    @DisplayName("Получение комментария по идентификатору")
    @Test
    void getCommentById() throws Exception {
        Comment comment = getExampleOfCommentDto();

        given(bookRepository.findById((String) any()))
                .willReturn(Mono.just(comment.getBook()));
        given(commentRepository.findById((String) any()))
                .willReturn(Mono.just(comment));

        var result = webTestClient.get()
                .uri("/api/comments/%s".formatted(FIRST_COMMENT_ID))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(CommentDto.class)
                .getResponseBody();

        var step = StepVerifier.create(result);
        StepVerifier.Step<CommentDto> stepResult = step.expectNext(mapper.toDto(comment));
        assertThat(stepResult).isNotNull();
        stepResult.verifyComplete();
    }

    @DisplayName("Ошибка получения комментария по идентификатору")
    @Test
    void getCommentWithException() throws Exception {
        given(commentRepository.findById((String) any())).willThrow(NotFoundException.class);

        webTestClient.get()
                .uri("/api/comments/%s".formatted(FIRST_COMMENT_ID))
                .exchange()
                .expectStatus().isNotFound();
    }

    @DisplayName("Сохранение комментария")
    @Test
    void saveComment() throws Exception {
        Comment comment = getExampleOfCommentDto();

        given(bookRepository.findById((String) any()))
                .willReturn(Mono.just(comment.getBook()));
        given(commentRepository.save(any()))
                .willReturn(Mono.just(comment));

        CommentCreateDto createDto = new CommentCreateDto(comment.getText(),
                comment.getBook().getId());

        var result = webTestClient.post()
                .uri("/api/comments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(CommentDto.class)
                .getResponseBody();

        var step = StepVerifier.create(result);
        StepVerifier.Step<CommentDto> stepResult = step.expectNext(mapper.toDto(comment));
        stepResult.verifyComplete();
    }

    @DisplayName("Ошибка сохранения комментария (с невалидным текстом)")
    @Test
    void getNotValidException() throws Exception {
        Comment comment = getExampleOfCommentDto();
        CommentCreateDto createDto = new CommentCreateDto(null,
                comment.getBook().getId());

        webTestClient.post()
                .uri("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @DisplayName("Ошибка сохранения комментария (с невалидным book_id)")
    @Test
    void getBookIdNotValidException() throws Exception {
        Comment comment = getExampleOfCommentDto();
        given(commentRepository.save(any()))
                .willReturn(Mono.just(comment));

        CommentCreateDto createDto = new CommentCreateDto("123",
                null);

        webTestClient.post()
                .uri("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @DisplayName("Обновление комментария")
    @Test
    void updateComment() throws Exception {
        Comment comment = getExampleOfCommentDto();

        given(bookRepository.findById((String) any()))
                .willReturn(Mono.just(comment.getBook()));
        given(commentRepository.findById((String) any()))
                .willReturn(Mono.just(comment));
        given(commentRepository.save(any())).willReturn(Mono.just(comment));

        CommentUpdateDto updateDto = new CommentUpdateDto(FIRST_COMMENT_ID,
                comment.getText(), comment.getBook().getId());

        var result = webTestClient.patch()
                .uri("/api/comments/".concat(FIRST_COMMENT_ID))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateDto)
                .exchange()
                .expectStatus().isAccepted()
                .returnResult(CommentDto.class)
                .getResponseBody();

        var step = StepVerifier.create(result);
        StepVerifier.Step<CommentDto> stepResult = step.expectNext(mapper.toDto(comment));

        assertThat(stepResult).isNotNull();
        stepResult.verifyComplete();
    }

    @DisplayName("Ошибка получения комментария, который требуется обновить")
    @Test
    void getNotFoundExceptionByUpdating() throws Exception {
        Comment comment = getExampleOfCommentDto();

        given(bookRepository.findById((String) any()))
                .willReturn(Mono.just(comment.getBook()));
        given(commentRepository.findById((String) any()))
                .willReturn(Mono.just(comment));
        given(commentRepository.save(any())).willThrow(NotFoundException.class);

        CommentUpdateDto updateDto = new CommentUpdateDto(comment.getId(), comment.getText(),
                comment.getBook().getId());

        webTestClient.patch()
                .uri("/api/comments/%s".formatted(FIRST_COMMENT_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateDto)
                .exchange()
                .expectStatus().isNotFound();
    }

    @DisplayName("Удаление комментария")
    @Test
    void deleteComment() throws Exception {
        given(commentRepository.deleteById((String) any())).willReturn(Mono.empty());

        var result = webTestClient
                .delete().uri("/api/comments/%s".formatted(FIRST_COMMENT_ID))
                .exchange()
                .expectStatus().isNoContent()
                .returnResult(Void.class)
                .getResponseBody();

        var step = StepVerifier.create(result);
        step.verifyComplete();
    }

    private Comment[] getExampleComments() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Comment(String.valueOf(id), "text %d".formatted(id),
                        new Book(String.valueOf(id), "title %d".formatted(id),
                                new Author("fullname %d".formatted(id)),
                                new Genre("name %d".formatted(id)))))
                .toArray(Comment[]::new);
    }

    private Comment getExampleOfCommentDto() {
        return new Comment(FIRST_COMMENT_ID, "c",
                new Book(FIRST_BOOK_ID, "b",
                        new Author("a"),
                        new Genre("g")));
    }
}