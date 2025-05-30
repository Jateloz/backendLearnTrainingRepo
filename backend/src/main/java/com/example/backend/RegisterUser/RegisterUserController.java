package com.example.backend.RegisterUser;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterUserController {
    private final RegisterUserService service;
    private final RegisterUserRepository repository;

    public RegisterUserController(RegisterUserService service, RegisterUserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/createAccount")
    public String registerUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") Long phone,
            @RequestParam("password") String password,
            @RequestParam("confirm") String confirm
    ){
        if (!password.equals(confirm)){
            return "redirect:/register?error=password mismatch";
        }
        try {
            service.registerUser(name,email,phone,password);
            return "redirect:/login";

        }catch (Exception e){
            return"redirect:/register?error="+e.getMessage();
        }
    }
    @PostMapping("/loginUser")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session
    ){
        RegisterUser user = repository.findByEmail(email);
        if (user == null){
            return "redirect:/login?error=invalid username or password";
        }else {
            //create the session
            session.setAttribute("user",user);
            return "redirect:/";
        }
    }
}
