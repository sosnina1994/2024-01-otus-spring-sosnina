package ru.otus.hw.repositories.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.hw.models.jpa.JpaAuthor;


public interface JpaAuthorRepository extends PagingAndSortingRepository<JpaAuthor, Long> {
}
