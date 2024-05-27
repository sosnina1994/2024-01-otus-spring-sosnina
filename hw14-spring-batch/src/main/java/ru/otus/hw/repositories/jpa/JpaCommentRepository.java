package ru.otus.hw.repositories.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.hw.models.jpa.JpaComment;

import java.util.List;

public interface JpaCommentRepository extends PagingAndSortingRepository<JpaComment, Long> {
    List<JpaComment> findAllByBookId(Long bookId);
}
