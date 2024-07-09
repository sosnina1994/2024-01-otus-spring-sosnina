package ru.otus.hw.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.dto.*;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.services.AuthorServiceImpl;
import ru.otus.hw.services.BookServiceImpl;
import ru.otus.hw.services.GenreServiceImpl;

import java.util.List;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Тестирование контроллера книг")
@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final long FIRST_BOOK_ID = 1L;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private AuthorServiceImpl authorService;

    @MockBean
    private GenreServiceImpl genreService;

    @DisplayName("Получение списка книг")
    @Test
    void getBooks() throws Exception {
        List<BookDto> exampleList = getExampleBookList();

        given(bookService.findAll()).willReturn(exampleList);
        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(exampleList)));
    }

    @DisplayName("Получение книги по идентификатору")
    @Test
    void getBookById() throws Exception {
        BookDto bookDto = getExampleOfBookDto();

        given(bookService.findById(any())).willReturn(bookDto);

        mvc.perform(get("/api/books/%d".formatted(bookDto.getId())))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookDto)));
    }

    @DisplayName("Ошибка получения книги по идентификатору")
    @Test
    void getNotFoundException() throws Exception {
        given(bookService.findById(any()))
                .willThrow(NotFoundException.class);

        mvc.perform(get("/api/books/%d".formatted(FIRST_BOOK_ID)))
                .andExpect(status().isNotFound());
    }

    @DisplayName("Сохранение книги")
    @Test
    void shouldAddNewBook() throws Exception {
        BookDto bookDto = getExampleOfBookDto();
        given(bookService.create(any()))
                .willReturn(bookDto);

        BookCreateDto bookCreateDto = new BookCreateDto(bookDto.getId(),
                bookDto.getTitle(), bookDto.getAuthor().getId(),
                bookDto.getGenre().getId());

        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(bookDto)));
    }

    @DisplayName("Получение ошибки невалидного автора по id")
    @Test
    void getInvalidAuthorId() throws Exception {
        BookDto bookDto = getExampleOfBookDto();
        BookCreateDto bookCreateDto = new BookCreateDto(bookDto.getId(),
                bookDto.getTitle(), null, bookDto.getGenre().getId());

        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookCreateDto)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Получение ошибки невалидного жанра по id")
    @Test
    void getInvalidGenreId() throws Exception {
        BookDto bookDto = getExampleOfBookDto();
        BookCreateDto bookCreateDto = new BookCreateDto(bookDto.getId(),
                bookDto.getTitle(), bookDto.getAuthor().getId(), null);

        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookCreateDto)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Обновление книги")
    @Test
    void updateBook() throws Exception {
        BookDto bookDto = getExampleOfBookDto();
        given(bookService.update(any()))
                .willReturn(bookDto);

        BookUpdateDto bookUpdateDto = new BookUpdateDto(bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor().getId(),
                bookDto.getGenre().getId());

        mvc.perform(patch("/api/books/%d".formatted(bookDto.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bookUpdateDto)))
                .andExpect(status().isAccepted())
                .andExpect(content().json(mapper.writeValueAsString(bookDto)));
    }

    @DisplayName("Обновление несуществующей книги")
    @Test
    void getNotFoundExceptionByUpdating() throws Exception {
        given(bookService.update(any())).willThrow(NotFoundException.class);

        BookDto bookDto = getExampleOfBookDto();
        BookUpdateDto bookUpdateDto = new BookUpdateDto(bookDto.getId(), bookDto.getTitle(),
                bookDto.getAuthor().getId(), bookDto.getGenre().getId());
        mvc.perform(patch("/api/books/%d".formatted(FIRST_BOOK_ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookUpdateDto)))
                .andExpect(status().isNotFound());
    }

    @DisplayName("Должен удалить книгу")
    @Test
    void shouldDeleteBook() throws Exception {
        mvc.perform(delete("/api/books/%d".formatted(FIRST_BOOK_ID)))
                .andExpect(status().isNoContent());
    }

    private List<BookDto> getExampleBookList() {
        return LongStream.range(1L, 4L).boxed()
                .map(id -> new BookDto(id, "title %d".formatted(id),
                        new AuthorDto(id, "name"), new GenreDto(id, "genre")))
                .toList();
    }

    private BookDto getExampleOfBookDto() {
        given(authorService.findAll()).willReturn(List.of(new AuthorDto(1L, "A")));
        given(genreService.findAll()).willReturn(List.of(new GenreDto(1L, "G")));

        return new BookDto(FIRST_BOOK_ID, "test_title",
                authorService.findAll().get(0),
                genreService.findAll().get(0));
    }
}