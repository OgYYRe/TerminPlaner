package ch.informatik.m223.TerminPlaner.controller;

import ch.informatik.m223.TerminPlaner.service.ReservationService;
import ch.informatik.m223.TerminPlaner.service.ReservationService.CodeType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class IndexController {

    // 1. Service injizieren
    private final ReservationService reservationService;

    public IndexController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Welcome to TerminPlaner");
        return "index";
    }

    @PostMapping("/check-code")
    public String checkCode(@RequestParam("code") String rawCode, RedirectAttributes ra) {

        // Code bereinigen (Trimmen und in Großbuchstaben, falls nötig)
        String code = rawCode.trim().toUpperCase();

        // 2. Code-Typ über den Service prüfen
        CodeType type = reservationService.checkCode(code);

        // 3. Entsprechend dem Typ weiterleiten
        switch (type) {
            case PRIVATE:
                return "redirect:/reservations/private/" + code;
            case PUBLIC:
                return "redirect:/reservations/public/" + code;
            case NOT_FOUND:
            default:
                // Fehlerbehandlung
                ra.addFlashAttribute("error", "Code nicht gefunden. Bitte prüfen Sie Ihre Eingabe.");
                return "redirect:/";
        }
    }
}