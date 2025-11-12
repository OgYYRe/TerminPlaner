package ch.informatik.m223.TerminPlaner.service;

import ch.informatik.m223.TerminPlaner.model.Reservation;
import ch.informatik.m223.TerminPlaner.model.Room;
import ch.informatik.m223.TerminPlaner.repository.ReservationRepository;
import ch.informatik.m223.TerminPlaner.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final CodeService codeService;

    public enum CodeType {
        PRIVATE, PUBLIC, NOT_FOUND
    }

    // Konstruktor-Injection für alle benötigten Abhängigkeiten
    public ReservationService(ReservationRepository reservationRepository,
                              RoomRepository roomRepository,
                              CodeService codeService) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.codeService = codeService;
    }

    /**
     * Prüft, ob eine Überlappung für den Raum und die Zeit existiert.
     */
    private boolean hasOverlap(Integer roomId, LocalDateTime startAt, LocalDateTime endAt) {
        List<Reservation> overlaps = reservationRepository
                .findByRoomIdAndStartAtBeforeAndEndAtAfter(roomId, endAt, startAt);
        return !overlaps.isEmpty();
    }

    /**
     * Hauptmethode zum Erstellen der Reservation.
     */
    public Reservation createReservation(String date, String fromTime, String toTime,
                                         Integer roomId, String remark, String participants)
            throws ReservationOverlapException, RoomNotFoundException {

        // 1. Eingaben in LocalDateTime umwandeln
        LocalDateTime startAt = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(fromTime));
        LocalDateTime endAt = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(toTime));

        // 2. Zeit-Validierung (Ende muss nach Anfang sein)
        if (!endAt.isAfter(startAt)) {
            throw new IllegalArgumentException("Endzeit muss nach der Startzeit liegen.");
        }

        // 3. Raum finden
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Raum nicht gefunden: " + roomId));

        // 4. Überlappungsprüfung
        if (hasOverlap(roomId, startAt, endAt)) {
            throw new ReservationOverlapException("Die Zeit ist in diesem Raum bereits belegt.");
        }

        // 5. Codes generieren
        String publicCode = codeService.generatePublicCode();
        String privateCode = codeService.generatePrivateCode();

        // 6. Neues Reservation-Objekt erstellen
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setStartAt(startAt);
        reservation.setEndAt(endAt);
        reservation.setRemark(remark);
        reservation.setPublicCode(publicCode);
        reservation.setPrivateCode(privateCode);
        reservation.setParticipants(participants);

        // 7. Speichern und zurückgeben
        return reservationRepository.save(reservation);
    }

    // Reservation aktualisieren
    @Transactional
    public Optional<Reservation> updateReservation(String privateCode,
                                                   String date, String fromTime, String toTime,
                                                   Integer roomId, String remark, String participants) {
        if (privateCode == null || privateCode.isBlank()) return Optional.empty();

        return reservationRepository.findByPrivateCode(privateCode.trim()).map(existing -> {
            // Eingaben parsen
            LocalDateTime newStart = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(fromTime));
            LocalDateTime newEnd   = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(toTime));
            if (!newEnd.isAfter(newStart)) throw new IllegalArgumentException("Endzeit muss nach der Startzeit liegen.");

            // Raum holen
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new RoomNotFoundException("Raum nicht gefunden: " + roomId));

            // Überlappung prüfen (anderen Datensatz ausschliessen)
            List<Reservation> overlaps = reservationRepository
                    .findByRoomIdAndStartAtBeforeAndEndAtAfter(roomId, newEnd, newStart);
            boolean conflict = overlaps.stream().anyMatch(r -> !r.getId().equals(existing.getId()));
            if (conflict) throw new ReservationOverlapException("Die Zeit ist in diesem Raum bereits belegt.");

            // Werte setzen
            existing.setRoom(room);
            existing.setStartAt(newStart);
            existing.setEndAt(newEnd);
            existing.setRemark(remark);
            existing.setParticipants(participants);

            return reservationRepository.save(existing);
        });
    }

    // Eigene Exception-Klassen (können in separaten Dateien erstellt werden)
    public static class ReservationOverlapException extends RuntimeException {
        public ReservationOverlapException(String message) { super(message); }
    }

    public static class RoomNotFoundException extends RuntimeException {
        public RoomNotFoundException(String message) { super(message); }
    }

    public CodeType checkCode(String rawCode) {
        if (rawCode == null || rawCode.isBlank()) {  // Eingabe prüfen
            return CodeType.NOT_FOUND;
        }
        String code = rawCode.trim();

        // Suche nach privatem Code
        Optional<Reservation> privateReservation = reservationRepository.findByPrivateCode(code);
        if (privateReservation.isPresent()) {
            return CodeType.PRIVATE;
        }

        // Suche nach öffentlichem Code
        Optional<Reservation> publicReservation = reservationRepository.findByPublicCode(code);
        if (publicReservation.isPresent()) {
            return CodeType.PUBLIC;
        }

        // Code nicht gefunden
        return CodeType.NOT_FOUND;
    }

    // Code anhand public/private Code finden
    @Transactional(readOnly = true)
    public Optional<Reservation> findByCode(String rawCode) {
        if (rawCode == null || rawCode.isBlank()) {  // Eingabe prüfen
            return Optional.empty();
        }
        String code = rawCode.trim();
        return  reservationRepository.findByPublicCode(code)
                .or(() -> reservationRepository.findByPrivateCode(code));

    }

    @Transactional
    public boolean deleteByPrivateCode(String rawCode) {
        if (rawCode == null || rawCode.isBlank()) {  // Eingabe prüfen
            return false;
        }
        String code = rawCode.trim();
        return reservationRepository.findByPrivateCode(code)
                .map(res -> {
                    reservationRepository.delete(res); // Datensatz löschen
                    return true;
                })
                .orElse(false); // Nichts gefunden
    }
        // Paginierte Liste holen
        @Transactional(readOnly = true)
        public Page<Reservation> listPaged(int page, int size) {
            // page: 0-basiert, size: z.B. 10
            Pageable pageable = PageRequest.of(page, size);
            return reservationRepository.findAllOrderByDateTime(pageable);
    }
}