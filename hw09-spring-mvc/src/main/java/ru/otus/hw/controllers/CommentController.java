package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.dto.CommentDto;
import ru.otus.hw.services.BookServiceImpl;
import ru.otus.hw.services.CommentServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;

    private final BookServiceImpl bookService;

    @GetMapping("/book_comments")
    public String getCommentsForBook(@RequestParam("id") long id, Model model) {
        BookDto book = bookService.findById(id);
        List<CommentDto> comments = commentService.findAllForBook(id);

        model.addAttribute("book", book);
        model.addAttribute("comments", comments);

        return "comments/book_comments";
    }
}
