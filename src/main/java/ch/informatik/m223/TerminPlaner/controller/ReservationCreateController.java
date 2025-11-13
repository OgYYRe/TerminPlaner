package ch.informatik.m223.TerminPlaner.controller;

import ch.informatik.m223.TerminPlaner.model.Reservation;
import ch.informatik.m223.TerminPlaner.service.ReservationService;
import ch.informatik.m223.TerminPlaner.service.ReservationService.ReservationOverlapException;
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
            @RequestParam("participants") String participants,
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

        } catch (ReservationOverlapException e) {
            // 2. FEHLERFALL: RAUM IST BESETZT
            redirectAttributes.addFlashAttribute("error", e.getMessage());

        } catch (IllegalArgumentException e) {
            // 3. FEHLERFALL: Ungültige Zeit (Ende vor Start)
            redirectAttributes.addFlashAttribute("error", "Ungültige Zeitangabe: " + e.getMessage());

        } catch (ReservationService.RoomNotFoundException e) {
            // 4. FEHLERFALL: Raum nicht gefunden
            redirectAttributes.addFlashAttribute("error", "Der ausgewählte Raum ist ungültig.");
        }

        // 5. Alte Eingaben zurückgeben, falls ein Fehler auftrat
        redirectAttributes.addFlashAttribute("oldDate", date);
        redirectAttributes.addFlashAttribute("oldFromTime", fromTime);
        redirectAttributes.addFlashAttribute("oldToTime", toTime);
        redirectAttributes.addFlashAttribute("oldRoomId", roomId);
        redirectAttributes.addFlashAttribute("oldRemark", remark);
        redirectAttributes.addFlashAttribute("oldParticipants", participants);

        return "redirect:/reservations/create";
    }

    @GetMapping("/reservations/success")
    public String showSuccessPage() {
        return "reservation_success";
    }
}