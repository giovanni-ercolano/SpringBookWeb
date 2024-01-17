package it.itsrizzoli.springbookweb.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByUserId(Integer id);

    Optional<Book> findById(int bookId);
}
