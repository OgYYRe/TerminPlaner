package ch.informatik.m223.TerminPlaner.controller;


import ch.informatik.m223.TerminPlaner.model.Reservation;
import ch.informatik.m223.TerminPlaner.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ReservationPrivateController {
    private final ReservationService reservationService;
    public ReservationPrivateController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations/private/{code}")
    public String showPrivateReservations(@PathVariable String code, Model model) {
        Optional<Reservation> reservation = reservationService.findByCode(code);
        if (reservation.isPresent()) {
            model.addAttribute("pageTitle", "Private Reservation");
            model.addAttribute("reservation", reservation.get());
            return "reservation_private";
        } else {
            return "redirect:/";
        }
    }

    // Reservation mit privatem Code löschen und zurück zur Startseite
    @GetMapping({"/reservation/delete/{privateCode}", "/reservations/private/{privateCode}/delete"}) // Ich hasse path variables
    public String deleteReservation(@PathVariable String privateCode, org.springframework.web.servlet.mvc.support.RedirectAttributes ra) {
        boolean deleted = reservationService.deleteByPrivateCode(privateCode);
        if (deleted){
            ra.addFlashAttribute("succes", "Die Reservation wurde erfolgreich gelöscht.");
        } else {
            ra.addFlashAttribute("error", "Die Reservation konnte nicht gelöscht werden." +
                    "Code ungültig oder bereits gelöscht.");
        }
        return "redirect:/";
    }
}

