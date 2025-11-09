package ch.informatik.m223.TerminPlaner.controller;


import ch.informatik.m223.TerminPlaner.service.CodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReservationCreateController {

    private final CodeService codeService;

    public ReservationCreateController(CodeService codeService) {
        this.codeService = codeService;
    }

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
            RedirectAttributes redirectAttributes) {

        // Codes erstellen
        String publicCode = codeService.generatePublicCode();
        String privateCode = codeService.generatePrivateCode();

        // Codes zu den Redirect-Attributen hinzufügen
        redirectAttributes.addFlashAttribute("publicCode", publicCode);
        redirectAttributes.addFlashAttribute("privateCode", privateCode);



        // Nachher Validierung und Speicherung der Reservation
        System.out.println("Date: " + date);
        System.out.println("Von: " + fromTime);
        System.out.println("Bis: " + toTime);
        System.out.println("Zimmer: " + roomId);
        System.out.println("Bemerkung: " + remark);
        System.out.println("Teilnehmer: " + participants);

        // Kleine Bestätigung Nachricht
        redirectAttributes.addFlashAttribute("message", "Reservation erfolgreich erstellt!");
        return "redirect:/reservations/success";
    }

    @GetMapping("/reservations/success")
    public String showSuccessPage() {
    return "reservation_success";

}
}
