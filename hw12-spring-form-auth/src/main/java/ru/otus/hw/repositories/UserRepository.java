package ru.otus.hw.repositories;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Nonnull
    Optional<User> findByUsername(String username);
}
