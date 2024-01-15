package it.itsrizzoli.springbookweb.controller;

import it.itsrizzoli.springbookweb.model.User;
import it.itsrizzoli.springbookweb.model.UserRepository;
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
    public String postLogin(LoginForm loginForm) {

        User user = userRepository.login(loginForm.username,loginForm.password);

        if(user != null){
            return "redirect:/home";
        }else{
            return "loginUser";
        }
    }

    @GetMapping("/home")
    public String showHome(Model m ) {
        m.addAttribute("libri",BookController.books); //da sostituire con la parte del DB
        return "home";
    }
}
