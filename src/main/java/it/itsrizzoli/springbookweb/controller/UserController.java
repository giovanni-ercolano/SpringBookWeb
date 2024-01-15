package it.itsrizzoli.springbookweb.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
@Controller
public class UserController {
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
        users.add(personaForm);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(LoginForm loginForm) {
        return "loginUser";
    }

    @PostMapping("/postLogin")
    public String postLogin(LoginForm loginForm) {
        boolean b = false;

        for (PersonForm pf : users){
            if(pf.username.equals(loginForm.username) && pf.password.equals(loginForm.password)){
                b = true;
            }
        }

        if(b){
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
