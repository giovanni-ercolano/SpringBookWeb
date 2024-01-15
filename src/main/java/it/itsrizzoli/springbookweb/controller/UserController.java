package it.itsrizzoli.springbookweb.controller;

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

import java.util.ArrayList;
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
    public String showHome(Model m ) {
        m.addAttribute("libri",bookRepository.findAll());
        return "home";
    }
}
