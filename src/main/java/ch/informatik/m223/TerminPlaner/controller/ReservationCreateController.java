package ch.informatik.m223.TerminPlaner.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationCreateController {

    @GetMapping("/reservations/create")
    public String showCreateForm(Model model) {
        model.addAttribute("pageTitle", "Neue Reservation erstellen");
        return "reservation_create";
    }

    //Verarbeitet das Formular zum Erstellen einer neuen Reservation
    @PostMapping("/reservations/create")
    public String handleCreateForm(
            @RequestParam("date") String date,
            @RequestParam("fromTime") String fromTime,
            @RequestParam("toTime") String toTime,
            @RequestParam("roomId") Integer roomId,
            @RequestParam("remark") String remark,
            @RequestParam("participants") String participants,
            Model model) {

        // Nachher Validierung und Speicherung der Reservation
        System.out.println("Date: " + date);
        System.out.println("Von: " + fromTime);
        System.out.println("Bis: " + toTime);
        System.out.println("Zimmer: " + roomId);
        System.out.println("Bemerkung: " + remark);
        System.out.println("Teilnehmer: " + participants);

        // Kleine Bestätigung Nachricht
        model.addAttribute("message", "Reservation wurde erfolgreich erstellt!");
        return "reservation_create";



    }

}
