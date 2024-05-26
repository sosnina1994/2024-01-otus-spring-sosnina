package ru.otus.hw.services;

import ru.otus.hw.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findByUsername(String username);

    void deleteUser(UserDto dto);
}
