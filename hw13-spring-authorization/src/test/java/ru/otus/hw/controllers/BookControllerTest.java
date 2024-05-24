package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.dto.*;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.security.SecurityConfiguration;
import ru.otus.hw.services.AuthorServiceImpl;
import ru.otus.hw.services.BookServiceImpl;
import ru.otus.hw.services.GenreServiceImpl;

import javax.sql.*;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование контроллера книг")
@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
class BookControllerTest {

    private static final long FIRST_BOOK_ID = 1L;

    private static final long NOT_CONTAIN_BOOK_ID = 1L;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private AuthorServiceImpl authorService;

    @MockBean
    private GenreServiceImpl genreService;

    @DisplayName("Редирект на страницу login")
    @Test
    void redirectToLoginPage() throws Exception {
        mvc.perform(get("/")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("Добавление новой книги")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void saveNewBook() throws Exception {
        BookDto book = getExampleOfBookDto();
        BookCreateDto bookCreateDto = new BookCreateDto(book.getTitle(), book.getAuthor().getId(),
                book.getGenre().getId());

        mvc.perform(post("/book")
                        .with(csrf())
                        .flashAttr("book", bookCreateDto))
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("Ошибку аутентификации для добавления новой книги")
    @Test
    void saveBookWith401() throws Exception {
        mvc.perform(get("/book"))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("Получение книги по идентификатору")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
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
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void getNotFoundExceptionByGetting() throws Exception {
        given(bookService.findById(NOT_CONTAIN_BOOK_ID))
                .willThrow(new NotFoundException(null));

        mvc.perform(get("/book/").param("id", String.valueOf(NOT_CONTAIN_BOOK_ID)))
                .andExpect(status().isNotFound());
    }


    @DisplayName("Обновление книги")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void updateBook() throws Exception {
        BookDto book = getExampleOfBookDto();
        given(bookService.findById(book.getId())).willReturn(book);

        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                book.getAuthor().getId(), book.getGenre().getId());

        Long id = 1L;
        mvc.perform(post("/book/{id}", id).with(csrf()).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("Ошибка аутентификации для обновления книги")
    @Test
    void updateBook401() throws Exception {
        Long id = 1L;
        mvc.perform(get("/book/{id}", id))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("Ошибка валидации id автора при создании книги")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void getValidIdAuthorException() throws Exception {
        BookDto book = getExampleOfBookDto();
        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                null, book.getGenre().getId());
        Long id = 1L;

        mvc.perform(post("/book/{id}", id).with(csrf()).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/book/1"));
    }

    @DisplayName("Ошибка валидации id жанра при создании книги")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void getValidIGenreException() throws Exception {
        BookDto book = getExampleOfBookDto();
        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                book.getAuthor().getId(), null);

        Long id = 1L;

        mvc.perform(post("/book/{id}", id).with(csrf()).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/book/1"));
    }

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("Удаление книги по id")
    @Test
    void deleteBook() throws Exception {
        mvc.perform(post("/delete").param("id", String.valueOf(FIRST_BOOK_ID)).with(csrf()))
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("Удаление книги по id")
    @Test
    void deleteBookWith401() throws Exception {

        mvc.perform(get("/delete").param("id", String.valueOf(FIRST_BOOK_ID)))
                .andExpect(status().isUnauthorized());
    }

    private BookDto getExampleOfBookDto() {
        given(authorService.findAll()).willReturn(List.of(new AuthorDto(1L, "A")));
        given(genreService.findAll()).willReturn(List.of(new GenreDto(1L, "G")));

        return new BookDto(FIRST_BOOK_ID, "a",
                authorService.findAll().get(0),
                genreService.findAll().get(0));
    }
}