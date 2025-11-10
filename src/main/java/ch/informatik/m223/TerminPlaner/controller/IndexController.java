package ch.informatik.m223.TerminPlaner.controller;

import ch.informatik.m223.TerminPlaner.service.ReservationService;
import ch.informatik.m223.TerminPlaner.service.ReservationService.CodeType;
import org.aspectj.apache.bcel.classfile.Code;
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
        String code = rawCode.trim(); //toUpperCase weg, da Codes case-sensitive ist

        // 2. Code-Typ über den Service prüfen
        CodeType type = reservationService.checkCode(rawCode);

        // 3. Entsprechend dem Typ weiterleiten
        if (type == CodeType.PUBLIC) {
            return "redirect:/reservations/public/" + code;}
        else if (type == CodeType.PRIVATE) {
            return "redirect:/reservations/private/" + code;
        } else {
                // Fehlerbehandlung
                ra.addFlashAttribute("error", "Code nicht gefunden. Bitte prüfen Sie Ihre Eingabe.");
                return "redirect:/";
        }
    }
}