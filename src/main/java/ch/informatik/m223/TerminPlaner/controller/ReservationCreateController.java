package ch.informatik.m223.TerminPlaner.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationCreateController {

    @GetMapping("/reservations/create")
    public String showCreateForm(Model model) {
        model.addAttribute("pageTitle", "Neue Reservation erstellen");
        return "reservation_create";
    }
}
