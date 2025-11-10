package ch.informatik.m223.TerminPlaner.controller;


import ch.informatik.m223.TerminPlaner.model.Reservation;
import ch.informatik.m223.TerminPlaner.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ReservationPublicController {
    private final ReservationService reservationService;
    public ReservationPublicController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations/public/{code}")
    public String showPublicReservations(@PathVariable String code, Model model) {
        Optional<Reservation> reservation = reservationService.findByCode(code);

        if (reservation.isPresent()) {
            model.addAttribute("pageTitle", "Öffentliche Reservation");
            model.addAttribute("reservation", reservation.get());
            return "reservation_public";
        } else {
            return "redirect:/";
        }
    }
}