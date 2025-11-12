package ch.informatik.m223.TerminPlaner.controller;

import ch.informatik.m223.TerminPlaner.model.Reservation;
import ch.informatik.m223.TerminPlaner.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

    private final ReservationService reservationService;

    public ReservationApiController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // JSON für Infinite-Scroll
    @GetMapping
    public Map<String, Object> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Reservation> p = reservationService.listPaged(page, size);

        List<Map<String, Object>> items = p.getContent().stream().map(r -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", r.getId());
            m.put("startAt", r.getStartAt());
            m.put("endAt", r.getEndAt());
            m.put("roomId", r.getRoom().getId());
            return m;
        }).toList();

        Map<String, Object> out = new HashMap<>();
        out.put("content", items);
        out.put("page", p.getNumber());
        out.put("totalPages", p.getTotalPages());
        out.put("empty", p.isEmpty());
        return out;
    }
}
