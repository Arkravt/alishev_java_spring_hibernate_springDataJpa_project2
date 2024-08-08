package ru.spring.project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.project2.models.Book;
import ru.spring.project2.models.Person;
import ru.spring.project2.repositories.PeopleRepository;
import org.hibernate.*;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    final private PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person updatedPerson, int id) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Person getPersonWithBooks(int id) {
        Person person = peopleRepository.findById(id).get();
        Hibernate.initialize(person.getBooks());
        return person;
    }

    public Optional<Person> findByFullNameAndIdNot(String fullName, int id) {
        return Optional.of(peopleRepository.findByFullNameAndIdNot(fullName, id)).stream().findAny();
    }

}
