package ru.spring.project2.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.project2.dao.PersonDao;
import ru.spring.project2.models.Person;
import ru.spring.project2.utils.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDao personDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.personValidator = personValidator;
    }


    @GetMapping()
    public String people(Model model) {
        model.addAttribute("people", personDao.index());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDao.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.get(id));
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult
    ) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDao.update(person, id);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.get(id));
        model.addAttribute("books", personDao.getBooksByPersonId(id));

        return "people/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        personDao.delete(id);
        return "redirect:/people";
    }
}
