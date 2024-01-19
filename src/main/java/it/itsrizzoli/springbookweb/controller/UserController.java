package it.itsrizzoli.springbookweb.controller;

import it.itsrizzoli.springbookweb.model.Book;
import it.itsrizzoli.springbookweb.model.BookRepository;
import it.itsrizzoli.springbookweb.model.User;
import it.itsrizzoli.springbookweb.model.UserRepository;
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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    ArrayList<PersonForm> users = new ArrayList<>();

    @GetMapping("/registration")
    public String showRegister(PersonForm personForm){
        return "registrationUser";
    }

    @PostMapping("/postRegistration")
    public String postRegistrazione(@Valid PersonForm personaForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registrationUser";
        }
        userRepository.save(new User(personaForm.name,personaForm.surname,personaForm.username,personaForm.password));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(LoginForm loginForm) {
        return "loginUser";
    }

    @PostMapping("/postLogin")
    public String postLogin(LoginForm loginForm, HttpSession session) {

        User user = userRepository.login(loginForm.username,loginForm.password);

        if(user != null){
            session.setAttribute("user", user);
            System.out.println(session.getAttribute("user"));
            return "redirect:/home";
        }else{
            return "loginUser";
        }
    }

    @GetMapping("/home")
    public String showHome(Model m , HttpSession session) {
        User user = (User) session.getAttribute("user");
        m.addAttribute("libri",bookRepository.findBooksByUserBooks(user.getId()));
        m.addAttribute("allBooks",bookRepository.findAll());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("", null);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfilo(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/editUser")
    public String mostraModificaForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/editPostUser")
    public String postModificaForm(User user, HttpSession session) {
        User editUser = (User) session.getAttribute("user");
        editUser.setName(user.getName());  
        editUser.setSurname(user.getSurname());
        editUser.setUsername(user.getUsername());
        editUser.setPassword(user.getPassword());

        userRepository.save(editUser);

        return "redirect:/profile";
    }

    @GetMapping("/delete")
    public String removeUser(HttpSession session){
        User user = (User) session.getAttribute("user");

        //bookRepository.saveAll(bookRepository.findBooksByUserId(user.getId()));

        userRepository.delete(user);
        session.setAttribute("user",null);

        return "redirect:/registration";
    }

}
