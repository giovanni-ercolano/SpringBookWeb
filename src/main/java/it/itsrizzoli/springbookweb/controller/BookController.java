package it.itsrizzoli.springbookweb.controller;

import it.itsrizzoli.springbookweb.model.BookRepository;
import it.itsrizzoli.springbookweb.model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    static ArrayList<Book> books = new ArrayList<>();

    @GetMapping("/createBook")
    public String createBook(Book book){
        return "newBook";
    }

    @PostMapping("/postStoreBook")
    public String storeBook(@Valid Book book, BindingResult bindingResult, Model model, HttpSession session){

        User user = (User) session.getAttribute("user");

        if(bindingResult.hasErrors()){
            return "newBook";
        }
        System.out.println("Controllo sessione:" + session.getAttribute("user"));
        book.setUser(user);
        bookRepository.save(book);
        return "redirect:/home";
    }
}
