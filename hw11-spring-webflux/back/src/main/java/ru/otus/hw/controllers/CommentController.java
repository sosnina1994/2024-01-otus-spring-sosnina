package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.dto.CommentCreateDto;
import ru.otus.hw.dto.CommentDto;
import ru.otus.hw.dto.CommentUpdateDto;
import ru.otus.hw.mappers.CommentMapper;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final CommentMapper mapper;

    private final BookRepository bookRepository;

    @GetMapping("/api/books/{id}/comments")
    public Flux<CommentDto> getCommentsForBook(@PathVariable("id") String id) {
        return commentRepository.findAllByBookId(id)
                .map(mapper::toDto);
    }

    @GetMapping("/api/comments/{comment_id}")
    public Mono<ResponseEntity<CommentDto>> getCommentById(@PathVariable("comment_id") String id) {
        return commentRepository.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PostMapping("/api/comments")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<CommentDto> addCommentForBook(@Valid @RequestBody CommentCreateDto dto) {
        return bookRepository.findById(dto.getBookId())
                .flatMap(book -> commentRepository.save(mapper.toModel(dto, book)))
                .map(mapper::toDto);
    }

    @PatchMapping("/api/comments/{comment_id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Mono<ResponseEntity<CommentDto>> updateComment(@PathVariable("comment_id") String commentId,
                                                          @Valid @RequestBody CommentUpdateDto dto) {
        dto.setId(commentId);
        return bookRepository.findById(dto.getBookId())
                .flatMap(book -> commentRepository.save(mapper.toModel(dto, book)))
                .map(comment -> new ResponseEntity<>(mapper.toDto(comment), HttpStatus.ACCEPTED))
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/api/comments/{comment_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteComment(@PathVariable("comment_id") String id) {
        return commentRepository.deleteById(id)
                .then(Mono.fromCallable(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }
}