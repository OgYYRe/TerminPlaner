package ch.informatik.m223.TerminPlaner.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.*;

@Entity
@Table(
        name = "reservations",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_private_code", columnNames = "private_code"),
                @UniqueConstraint(name = "uq_public_code",  columnNames = "public_code")
        },
        indexes = {
                @Index(name = "idx_reservations_room_date", columnList = "room_id, date_")
        }
)
@Check(constraints = "time_from < time_to")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Zugehöriger Raum */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_reservation_room"))
    private Room room;

    /** Datum der Reservation (Spaltenname in DB ist 'date_') */
    @Column(name = "date_", nullable = false)
    private LocalDate date;

    @Column(name = "time_from", nullable = false)
    private LocalTime timeFrom;

    @Column(name = "time_to", nullable = false)
    private LocalTime timeTo;

    /** Bemerkung (in Vorgabe 10–200 Zeichen – Länge in DB 200) */
    @Column(length = 200)
    private String remark;

    /** Teilnehmende als Freitext (nur Buchstaben/Komma validierst du besser im DTO) */
    @Lob
    @Column(name = "participants_text", nullable = false)
    private String participantsText;

    /** Codes zum Zugriff (Public = read-only, Private = edit/delete) */
    @Column(name = "private_code", nullable = false, length = 36)
    private String privateCode;

    @Column(name = "public_code", nullable = false, length = 36)
    private String publicCode;

    /** Timestamps (werden unten automatisch gesetzt) */
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }
}