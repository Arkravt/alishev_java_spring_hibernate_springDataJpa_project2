package ru.spring.project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.project2.models.Book;
import ru.spring.project2.models.Person;
import ru.spring.project2.repositories.BooksRepository;
import ru.spring.project2.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void assign(int id, int personId) {
        Book book = booksRepository.findById(id).get();
        Person person = peopleService.findOne(personId);
        book.setOwner(person);
    }

    @Transactional
    public void release(int id) {
        Book book = booksRepository.findById(id).get();
        book.setOwner(null);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book book = booksRepository.findById(id).get();
        book.setId(id);
        book.setName(updatedBook.getName());
        book.setYear(updatedBook.getYear());
        book.setAuthor(updatedBook.getAuthor());
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
