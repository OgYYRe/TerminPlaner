package ch.informatik.m223.TerminPlaner.repository;

import ch.informatik.m223.TerminPlaner.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Findet alle Reservationen für einen bestimmten Raum, die sich mit dem
     * angegebenen Zeitfenster überschneiden.
     *
     * Eine Überlappung liegt vor, wenn:
     * (start_at < new_end_at) AND (end_at > new_start_at)
     */
    List<Reservation> findByRoomIdAndStartAtBeforeAndEndAtAfter(
            Integer roomId,
            LocalDateTime newEndAt,
            LocalDateTime newStartAt
    );
}
