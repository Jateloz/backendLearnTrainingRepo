package com.example.backend;

import com.example.backend.RegisterUser.RegisterUser;
import com.example.backend.payment.MpesaPayment;
import com.example.backend.payment.MpesaPaymentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PagesController {
    @Autowired
    private MpesaPaymentRepository mpesaPaymentRepository;

    @GetMapping("/")
    public String index(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user==null){
            return "redirect:/login";
        }else {
            model.addAttribute("name",user.getName());
            model.addAttribute("email",user.getEmail());
            return "index";
        }
    }
    @GetMapping("/about")
    public String about(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }else {
            model.addAttribute("name",user.getName());
            model.addAttribute("email",user.getEmail());
            return "about";
        }
    }
    @GetMapping("/properties")
    public String property(HttpSession session, Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }else {
            model.addAttribute("name",user.getName());
            model.addAttribute("email",user.getEmail());
            return "properties";
        }
    }
    @GetMapping("/booking")
    public String booking(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }else {
            model.addAttribute("name",user.getName());
            model.addAttribute("email",user.getEmail());
            return "booking";
        }
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/contact")
    public String contact(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }else {
            model.addAttribute("name",user.getName());
            model.addAttribute("email",user.getEmail());
            return "contact";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user==null){
            return "redirect:/";
        }else {
            session.invalidate();
            return "login";
        }
    }
    @GetMapping("/payment")
    public String payment(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }else {
            return "payment";
        }
    }
    @GetMapping("/reports")
    public String reports(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }else {
            List<MpesaPayment> mpesaPayments = mpesaPaymentRepository.findAll();
            model.addAttribute("payments",mpesaPayments);
            return "reports";
        }
    }
    @GetMapping("/mpesa")
    public String mpesa(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }else {
            return "mpesa";
        }
    }
    @GetMapping("/paymentSuccess")
    public String paymentSuccess(HttpSession session,Model model){
        RegisterUser user = (RegisterUser) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }else {
            return "paymentSuccess";
        }
    }
}
