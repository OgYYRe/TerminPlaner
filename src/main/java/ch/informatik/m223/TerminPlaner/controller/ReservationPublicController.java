package ch.informatik.m223.TerminPlaner.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReservationPublicController {

    @GetMapping("/reservations/public/{code}")
    public String showPublicReservations(@PathVariable String code, Model model ) {
        model.addAttribute("pageTitle", "Öffentliche Reservationen");
        model.addAttribute("publicCode", code);
        return "reservation_public";

    }
}
