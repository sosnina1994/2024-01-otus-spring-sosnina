package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.BookCreateDto;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.dto.BookUpdateDto;
import ru.otus.hw.services.AuthorService;
import ru.otus.hw.services.BookService;
import ru.otus.hw.services.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String allBooksList(Model model) {
        List<BookDto> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books/all_books";
    }

    @GetMapping("/book")
    @PreAuthorize("hasRole('ADMIN')")
    public String addBook(Model model) {
        BookCreateDto book = new BookCreateDto(null, null, null);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "books/add_book";
    }

    @GetMapping("/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editBook(@PathVariable(value = "id", required = false) Long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("book", BookUpdateDto.fromBookDto(book));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "books/edit_book";
    }

    @PostMapping("/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateBook(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("book") BookUpdateDto book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/book/" + id;
        }

        bookService.update(book);
        return "redirect:/";
    }


    @PostMapping("/book")
    @PreAuthorize("hasRole('ADMIN')")
    public String createBook(@Valid @ModelAttribute("book") BookCreateDto book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/add_book";
        }

        bookService.create(book);
        return "redirect:/";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@RequestParam("id") long id, Model model) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}
