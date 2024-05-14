package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.dto.BookCreateDto;
import ru.otus.hw.dto.BookUpdateDto;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.mappers.BookMapper;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.GenreRepository;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    private final BookMapper mapper;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @GetMapping("/api/books")
    public Flux<BookDto> allBooksList() {
        return bookRepository.findAll()
                .map(mapper::toDto);
    }

    @GetMapping("/api/books/{id}")
    public Mono<ResponseEntity<BookDto>> getBook(@PathVariable(value = "id", required = false) String id) {
        return bookRepository.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PatchMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ResponseEntity<BookDto>> updateBook(@PathVariable("id") String id,
                                                    @Valid @RequestBody BookUpdateDto book) {
        book.setId(id);

        final String authorId = book.getAuthorId();
        final String genreId = book.getGenreId();

        return bookRepository.findById(id)
                .flatMap(findedBook -> authorRepository.findById(authorId)
                        .flatMap(author -> genreRepository.findById(genreId)
                                .flatMap(genre -> bookRepository
                                        .save(mapper.toModel(book, author, genre))
                                )
                                .map(getBook -> new ResponseEntity<>(mapper.toDto(getBook), HttpStatus.ACCEPTED))
                                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()))
                        )
                );
    }

    @PostMapping("/api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<BookDto>> createBook(@Valid @RequestBody BookCreateDto book) {

        final String authorId = book.getAuthorId();
        final String genreId = book.getGenreId();

        return authorRepository.findById(authorId)
                .flatMap(author -> genreRepository.findById(genreId)
                        .flatMap(genre -> bookRepository
                                .save(mapper.toModel(book, author, genre))
                        )
                        .map(getBook -> new ResponseEntity<>(mapper.toDto(getBook), HttpStatus.CREATED))
                        .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()))
                );
    }

    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable("id") String id) {
        return bookRepository.deleteById(id)
                .then(Mono.fromCallable(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }
}