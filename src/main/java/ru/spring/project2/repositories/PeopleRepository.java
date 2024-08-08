package ru.spring.project2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.project2.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Person findByFullNameAndIdNot(String fullName, int id);

}
