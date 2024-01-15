package it.itsrizzoli.springbookweb.controller;

import it.itsrizzoli.springbookweb.model.BookRepository;
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
    public String storeBook(@Valid Book book, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "newBook";
        }
        bookRepository.save(new Book(book.title,book.author,book.description));
        model.addAttribute("libri",books);
        return "redirect:/home";
    }
}
