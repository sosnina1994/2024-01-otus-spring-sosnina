package ru.otus.hw.repositories.jpa;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.hw.models.jpa.JpaBook;

import java.util.List;
import java.util.Optional;

public interface JpaBookRepository extends PagingAndSortingRepository<JpaBook, Long> {
    @Nonnull
    @EntityGraph("book-graph")
    List<JpaBook> findAll();

    @Nonnull
    @EntityGraph("book-graph")
    Optional<JpaBook> findById(@Nonnull Long id);
}
