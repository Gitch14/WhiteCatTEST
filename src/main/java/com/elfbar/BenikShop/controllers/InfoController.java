package com.elfbar.BenikShop.controllers;

import com.elfbar.BenikShop.captcha.Captcha;
import com.elfbar.BenikShop.repo.EmailRepository;
import com.elfbar.BenikShop.essences.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InfoController {


    @Autowired
    private EmailRepository emailRepository;

    Captcha cap = new Captcha();
    String neBot = cap.getList();

    @GetMapping("/faq")
    public String about(Model model) {
        return "faq";
    }


    @GetMapping("/buy")
    public String buyPage(Model model) {
        model.addAttribute("cap", neBot);
        return "buy-page";
    }



    @PostMapping("/buy")
    public String emailInfo(@RequestParam String mail, @RequestParam String name, @RequestParam String phone, @RequestParam String text,@RequestParam String captcha,Model model){
       if (captcha.equals(neBot)){
            Email email = new Email(mail, name, phone, text);
            emailRepository.save(email);
        }
        return "redirect:/";
    }



}

