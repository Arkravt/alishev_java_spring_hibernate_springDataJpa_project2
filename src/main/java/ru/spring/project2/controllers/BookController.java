package ru.spring.project2.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.project2.dao.BookDao;
import ru.spring.project2.dao.PersonDao;
import ru.spring.project2.models.Book;
import ru.spring.project2.models.Person;
import ru.spring.project2.service.BooksService;
import ru.spring.project2.service.PeopleService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao, BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String books(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "book/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book/new";
        }

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = booksService.findOne(id);

        model.addAttribute(book);

        if (book.getOwner() == null) {
            model.addAttribute("people", peopleService.findAll());
        } else {
            model.addAttribute("owner", book.getOwner());
        }

        return "book/show";
    }

    @PatchMapping("{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.assign(id, person.getId());
        return "redirect:/books/{id}";
    }

    @PatchMapping("{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books/{id}";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id));
        return "book/edit";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book/edit";
        }

        booksService.update(id, book);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

}
