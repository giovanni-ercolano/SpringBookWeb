package it.itsrizzoli.springbookweb.dao;

import it.itsrizzoli.springbookweb.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select s from User s where username= :username and password= :password")
    public User login(String username, String password);
}
