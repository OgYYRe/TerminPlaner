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

    // Reservation mit privatem Code löschen, Info, zurück zur Startseite
    @GetMapping({"/reservation/delete/{privateCode}", "/reservations/private/{privateCode}/delete"}) // Ich hasse path variables
    public String deleteReservation(@PathVariable String privateCode, Model model) {
        boolean deleted = reservationService.deleteByPrivateCode(privateCode);
        if (deleted) {
            model.addAttribute("message", "Reservation wurde gelöscht. Sie werden weitergeleitet...");
            model.addAttribute("redirectUrl", "/"); // Ziel: Startseite
        } else {
            model.addAttribute("error", "Löschen nicht möglich. " +
                    "Code ungültig oder bereits gelöscht.");
        }
        return "reservation_private";
    }

    @GetMapping({"/reservation/edit/{privateCode}", "/reservations/private/{privateCode}/edit"})
    public String showEditForm(@PathVariable String privateCode, Model model) {
        return reservationService.findByCode(privateCode)
                .map(reservation -> {
                    model.addAttribute("pageTitle", "Reservation bearbeiten");
                    model.addAttribute("reservation", reservation);
                    return "reservation_edit";
                })
                .orElse("redirect:/");

    }

    // Edit Speichern
    @org.springframework.web.bind.annotation.PostMapping("/reservation/edit/{privateCode}")
    public String saveEdit(@org.springframework.web.bind.annotation.PathVariable String privateCode,
                           @org.springframework.web.bind.annotation.RequestParam String date,
                           @org.springframework.web.bind.annotation.RequestParam String fromTime,
                           @org.springframework.web.bind.annotation.RequestParam String toTime,
                           @org.springframework.web.bind.annotation.RequestParam Integer roomId,
                           @org.springframework.web.bind.annotation.RequestParam String remark,
                           @org.springframework.web.bind.annotation.RequestParam String participants,
                           org.springframework.ui.Model model) {
        try {
            var updatedOpt = reservationService.updateReservation(privateCode, date, fromTime, toTime, roomId, remark, participants);
            if (updatedOpt.isPresent()) {
                model.addAttribute("pageTitle", "Reservation bearbeiten");
                model.addAttribute("reservation", updatedOpt.get());
                model.addAttribute("message", "Änderungen gespeichert. Sie werden weitergeleitet...");
                model.addAttribute("redirectUrl", "/reservations/private/" + privateCode);
                return "reservation_edit";
            } else {
                model.addAttribute("error", "Code ungültig.");
                return "reservation_edit";
            }
        } catch (ReservationService.RoomNotFoundException | ReservationService.ReservationOverlapException | IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            // Formular mit alten Werten erneut anzeigen
            reservationService.findByCode(privateCode).ifPresent(res -> model.addAttribute("reservation", res));
            model.addAttribute("pageTitle", "Reservation bearbeiten");
            return "reservation_edit";
        }
    }
}

