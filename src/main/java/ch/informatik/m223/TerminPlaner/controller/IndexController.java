package ch.informatik.m223.TerminPlaner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Welcome to TerminPlaner");
        return "index";
    }

    // TODO: to implement
    @PostMapping("/check-code")
    public String checkCode(@RequestParam("code") String rawCode, RedirectAttributes ra) {return "redirect:/reservations/private/" + rawCode;}
}
