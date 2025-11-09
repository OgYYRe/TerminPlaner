package ch.informatik.m223.TerminPlaner.controller;

import ch.informatik.m223.TerminPlaner.model.Reservation;
import ch.informatik.m223.TerminPlaner.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReservationCreateController {

    // CodeService wird jetzt vom ReservationService verwaltet
    private final ReservationService reservationService;

    public ReservationCreateController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations/create")
    public String showCreateForm(Model model) {
        model.addAttribute("pageTitle", "Neue Reservation erstellen");
        return "reservation_create";
    }

    @PostMapping("/reservations/create")
    public String handleCreateForm(
            @RequestParam("date") String date,
            @RequestParam("fromTime") String fromTime,
            @RequestParam("toTime") String toTime,
            @RequestParam("roomId") Integer roomId,
            @RequestParam("remark") String remark,
            @RequestParam("participants") String participants, // Wird noch nicht gespeichert!
            RedirectAttributes redirectAttributes) {

        try {
            // Service aufrufen, um die Reservation zu erstellen und zu speichern
            Reservation savedReservation = reservationService.createReservation(
                    date, fromTime, toTime, roomId, remark, participants
            );

            // Codes aus dem gespeicherten Objekt holen
            redirectAttributes.addFlashAttribute("publicCode", savedReservation.getPublicCode());
            redirectAttributes.addFlashAttribute("privateCode", savedReservation.getPrivateCode());
            redirectAttributes.addFlashAttribute("message", "Reservation erfolgreich erstellt!");

            return "redirect:/reservations/success";

        } catch (ReservationService.ReservationOverlapException | IllegalArgumentException e) {
            // Fehlerfall 1: Überlappung oder ungültige Zeit
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            // Daten an das Formular zurückgeben, damit der Benutzer sie nicht erneut eingeben muss
            redirectAttributes.addFlashAttribute("oldDate", date);
            redirectAttributes.addFlashAttribute("oldFromTime", fromTime);
            // ... (weitere 'old' Werte hinzufügen)
            return "redirect:/reservations/create";

        } catch (ReservationService.RoomNotFoundException e) {
            // Fehlerfall 2: Raum existiert nicht (sollte durch <select> nicht passieren)
            redirectAttributes.addFlashAttribute("error", "Der ausgewählte Raum ist ungültig.");
            return "redirect:/reservations/create";
        }
    }

    @GetMapping("/reservations/success")
    public String showSuccessPage() {
        return "reservation_success";
    }
}