package it.itsrizzoli.springbookweb.model;

import it.itsrizzoli.springbookweb.controller.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
