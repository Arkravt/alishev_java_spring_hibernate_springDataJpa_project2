package ru.spring.project2.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.spring.project2.dao.PersonDao;
import ru.spring.project2.models.Person;

@Component
public class PersonValidator implements Validator {

    private PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = (Person) target;

        if(personDao.get(person.getId(), person.getFullName()).isPresent()) {
            errors.rejectValue("fullName","","Это ФИО уже существует");
        }

    }
}
