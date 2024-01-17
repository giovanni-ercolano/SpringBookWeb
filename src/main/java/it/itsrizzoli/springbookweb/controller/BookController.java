package it.itsrizzoli.springbookweb.controller;

import it.itsrizzoli.springbookweb.model.Book;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/detailBook")
    public String dettaglioBook(@RequestParam("bookId") Integer bookId, Model m){

        Optional<Book> bookDetail = bookRepository.findById(bookId);
        Book book = null;

        if (bookDetail.isPresent()) {
            book = bookDetail.get();
        }

        m.addAttribute("bookDT",book);
        return "bookDetail";
    }

    @GetMapping("/editBook")
    public String mostraModificaForm(@RequestParam("bookId") int bookId, Model model) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            Book libro = bookOptional.get();
            model.addAttribute("book", libro);
        }
        return "editBook";
    }

    @PostMapping("editBookPost")
    public String mostraModificaPost(@Valid Book book,BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "editBook";
        }
        Optional<Book> bookOptional = bookRepository.findById(book.getId());


        if (bookOptional.isPresent()) {
            Book libro = bookOptional.get();
            libro.setTitle(book.getTitle());
            libro.setAuthor(book.getAuthor());
            libro.setDescription(book.getDescription());
            System.out.printf("sono dentro bookOptional" + libro);
            bookRepository.save(libro);
        }
        return "redirect:/home";
    }

    @GetMapping("/remove")
    public String removeBook(@RequestParam("bookId") Integer bookId){
        Optional<Book> removeBook = bookRepository.findById(bookId);

        if (removeBook.isPresent()) {
            Book book = removeBook.get();
            bookRepository.delete(book);
        }

        return "redirect:/home";
    }
}
