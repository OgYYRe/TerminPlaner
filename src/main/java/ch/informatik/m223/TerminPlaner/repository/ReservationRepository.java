package ch.informatik.m223.TerminPlaner.repository;

import ch.informatik.m223.TerminPlaner.model.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    Optional<Reservation> findByPrivateCode(String privateCode);

    Optional<Reservation> findByPublicCode(String publicCode);

    @Query("select r from Reservation r " +
            "order by FUNCTION('date', r.startAt) asc, FUNCTION('time', r.startAt) asc, r.id asc")
    Page<Reservation> findAllOrderByDateTime(Pageable pageable);


}
