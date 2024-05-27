package ru.otus.hw.repositories.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.hw.models.jpa.JpaGenre;


public interface JpaGenreRepository extends PagingAndSortingRepository<JpaGenre, Long> {
}
