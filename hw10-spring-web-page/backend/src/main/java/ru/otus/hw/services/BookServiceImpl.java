package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.BookCreateDto;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.dto.BookUpdateDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.BookMapper;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book with id = %d is not found"
                        .formatted(id))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream().map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookDto create(BookCreateDto bookDto) {
        final Long authorId = bookDto.getAuthorId();
        final Long genreId = bookDto.getGenreId();

        var author = authorRepository.findById(authorId)
                .orElseThrow(() ->
                        new NotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() ->
                        new NotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = bookMapper.toModel(bookDto, author, genre);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    @Override
    public BookDto update(BookUpdateDto bookDto) {
        final Long id = bookDto.getId();
        final String title = bookDto.getTitle();
        final Long authorId = bookDto.getAuthorId();
        final Long genreId = bookDto.getGenreId();

        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id %d not found".formatted(id)));

        if (title != null) {
            book.setTitle(title);
        }

        if (authorId != null) {
            book.setAuthor(authorRepository.findById(authorId)
                    .orElseThrow(() -> new NotFoundException("Author with id %d not found".formatted(authorId))));
        }

        if (genreId != null) {
            book.setGenre(genreRepository.findById(genreId)
                    .orElseThrow(() -> new NotFoundException("Genre with id %d not found".formatted(genreId))));
        }

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
