package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.hw.dto.AuthorDto;
import ru.otus.hw.services.AuthorServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @GetMapping("/authors")
    public String allAuthorsList(Model model) {
        List<AuthorDto> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors/author_list";
    }
}
