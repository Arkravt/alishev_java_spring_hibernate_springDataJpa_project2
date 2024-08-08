package ru.spring.project2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.spring.project2.models.Book;
import ru.spring.project2.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> get(int id, String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName=? AND id<>?",
                new Object[]{fullName, id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

    }

    public Person get(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO person(fullname, yearbirth) VALUES (?, ?)",
                person.getFullName(), person.getYearBirth());
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE Person SET fullname=?, yearbirth=?  WHERE id=?",
                person.getFullName(), person.getYearBirth(), id);
    }

    public List<Book> getBooksByPersonId(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",
                new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

}
