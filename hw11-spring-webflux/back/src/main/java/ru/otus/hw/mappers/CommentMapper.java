package ru.otus.hw.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.dto.CommentCreateDto;
import ru.otus.hw.dto.CommentUpdateDto;
import ru.otus.hw.dto.CommentDto;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;

@Component
@AllArgsConstructor
public class CommentMapper {

    public Comment toModel(CommentCreateDto dto, Book book) {
        return new Comment(null, dto.getText(),
                book);
    }

    public Comment toModel(CommentUpdateDto dto, Book book) {
        return new Comment(dto.getId(), dto.getText(),
                book);
    }

    public CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText(),
                comment.getBook().getId());
    }
}
