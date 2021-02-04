package ir.webstack.blog.web.controller;

import ir.webstack.blog.dao.UserRepository;
import ir.webstack.blog.security.RegistrationForm;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UserController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping(value = "/register")
    public String registerForm(){
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistration(RegistrationForm form){
        userRepository.save(form.toUser(encoder));
        return "redirect:/user/login";
    }

    @GetMapping(value = "/login")
    public String loginForm(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/logout")
    public String logout(){
        return "redirect:/";
    }
}
