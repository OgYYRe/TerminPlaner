package ch.informatik.m223.TerminPlaner.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReservationPrivateController {

    @GetMapping("/reservations/private/{code}")
    public String showPrivateReservations(@PathVariable String code, Model model) {
        model.addAttribute("pageTitle", "Private Reservationen");
        model.addAttribute("privateCode", code);
        return "reservation_private";
    }
}
