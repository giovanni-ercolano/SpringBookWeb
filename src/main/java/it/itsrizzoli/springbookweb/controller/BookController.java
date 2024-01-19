package it.itsrizzoli.springbookweb.controller;

import it.itsrizzoli.springbookweb.model.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserBookRepository userBookRepository;
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
            libro.setPublicationDate(book.getPublicationDate());
            libro.setPrice(book.getPrice());
            System.out.printf("sono dentro bookOptional:" + libro);
            bookRepository.save(libro);
        }
        return "redirect:/home";
    }

    @GetMapping("/removeBook")
    public String removeBook(@RequestParam("bookId") Integer bookId){
        Optional<Book> removeBook = bookRepository.findById(bookId);

        if (removeBook.isPresent()) {
            Book book = removeBook.get();
            bookRepository.delete(book);
        }

        return "redirect:/home";
    }

    @RequestMapping("/addBooks")
    public String addBooks(@RequestParam("bookId") Integer bookId,HttpSession session) {
        Optional<Book> addBook = bookRepository.findById(bookId);

        User user = (User) session.getAttribute("user");
        Book book = null;
        if (addBook.isPresent()) {
            book = addBook.get();
        }
        UserBook userBook = new UserBook(book, user);
        userBookRepository.save(userBook);

        return "redirect:/home";
    }

    @RequestMapping ("/removeUserBook")
    public String removeBook(@RequestParam("bookId") Integer bookId,HttpSession session) {
        User user = (User) session.getAttribute("user");
        Optional<UserBook> removeBook = userBookRepository.findBooksByUserBooks(user.getId(),bookId);

        if (removeBook.isPresent()) {
            UserBook userBook = removeBook.get();
            userBookRepository.delete(userBook);
        }

        return "redirect:/home";
    }
}
