package ru.spring.project2.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.spring.project2.models.Book;
import ru.spring.project2.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public Book get(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void assign(int id, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", personId, id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
    }

    public void update(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
                book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Person JOIN Book ON Person.id = Book.person_id WHERE book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

}
