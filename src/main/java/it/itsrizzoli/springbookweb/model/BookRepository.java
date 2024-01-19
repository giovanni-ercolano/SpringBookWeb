package it.itsrizzoli.springbookweb.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Optional<Book> findById(int bookId);
    @Query("select b from Book b inner join UserBook ub on b.id=ub.book.id and ub.user.id=:userId")
    List<Book> findBooksByUserBooks(@Param("userId") Integer userId);


}
