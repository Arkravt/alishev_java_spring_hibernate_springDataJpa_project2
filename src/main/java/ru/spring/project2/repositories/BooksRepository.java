package ru.spring.project2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.project2.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
