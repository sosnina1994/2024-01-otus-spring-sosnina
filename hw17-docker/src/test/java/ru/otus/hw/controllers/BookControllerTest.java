package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.dto.*;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.services.AuthorServiceImpl;
import ru.otus.hw.services.BookServiceImpl;
import ru.otus.hw.services.GenreServiceImpl;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование контроллера книг")
@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final long FIRST_BOOK_ID = 1L;

    private static final long NOT_CONTAIN_BOOK_ID = 1L;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private AuthorServiceImpl authorService;

    @MockBean
    private GenreServiceImpl genreService;

    @DisplayName("Добавление новой книги")
    @Test
    void saveNewBook() throws Exception {
        BookDto book = getExampleOfBookDto();
        BookCreateDto bookCreateDto = new BookCreateDto(book.getTitle(), book.getAuthor().getId(),
                book.getGenre().getId());

        mvc.perform(post("/book").flashAttr("book", bookCreateDto))
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("Получение книги по идентификатору")
    @Test
    void getBookById() throws Exception {
        BookDto bookDto = new BookDto(FIRST_BOOK_ID, "B",
                new AuthorDto(1L, "A"), new GenreDto(1L, "G"));

        given(bookService.findById(FIRST_BOOK_ID))
                .willReturn(bookDto);

        mvc.perform(get("/book/{id}", FIRST_BOOK_ID))
                .andExpect(status().isOk());
    }

    @DisplayName("Получение ошибки при запросе получения книги")
    @Test
    void getNotFoundExceptionByGetting() throws Exception {
        given(bookService.findById(NOT_CONTAIN_BOOK_ID))
                .willThrow(new NotFoundException(null));

        mvc.perform(get("/book/").param("id", String.valueOf(NOT_CONTAIN_BOOK_ID)))
                .andExpect(status().isNotFound());
    }

    @DisplayName("Обновление книги")
    @Test
    void updateBook() throws Exception {
        BookDto book = getExampleOfBookDto();
        given(bookService.findById(book.getId())).willReturn(book);

        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                book.getAuthor().getId(), book.getGenre().getId());

        Long id = 1L;
        mvc.perform(post("/book/{id}", id).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("Получение ошибки при запросе обновления книги")
    @Test
    void getNotFoundExceptionByUpdating() throws Exception {
        BookUpdateDto bookUpdateDto = new BookUpdateDto(null, null,
                null, null);
        Long id = 1L;

        mvc.perform(post("/book/{id}", id).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/book/1"));
    }

    @DisplayName("Ошибка валидации id автора при создании книги")
    @Test
    void getValidIdAuthorException() throws Exception {
        BookDto book = getExampleOfBookDto();
        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                null, book.getGenre().getId());
        Long id = 1L;

        mvc.perform(post("/book/{id}", id).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/book/1"));
    }

    @DisplayName("Ошибка валидации id жанра при создании книги")
    @Test
    void getValidIGenreException() throws Exception {
        BookDto book = getExampleOfBookDto();
        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                book.getAuthor().getId(), null);

        Long id = 1L;

        mvc.perform(post("/book/{id}", id).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/book/1"));
    }

    @DisplayName("Удаление книги по id")
    @Test
    void deleteBook() throws Exception {
        mvc.perform(post("/delete").param("id", String.valueOf(FIRST_BOOK_ID)))
                .andExpect(redirectedUrl("/"));
    }

    private BookDto getExampleOfBookDto() {
        given(authorService.findAll()).willReturn(List.of(new AuthorDto(1L, "A")));
        given(genreService.findAll()).willReturn(List.of(new GenreDto(1L, "G")));

        return new BookDto(FIRST_BOOK_ID, "a",
                authorService.findAll().get(0),
                genreService.findAll().get(0));
    }
}