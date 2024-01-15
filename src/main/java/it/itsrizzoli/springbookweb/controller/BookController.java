package it.itsrizzoli.springbookweb.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class BookController {

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
        books.add(book);
        System.out.println("newBook" + book.toString());
        //model.addAttribute("libri",books);
        return "redirect:/home";
    }
}
