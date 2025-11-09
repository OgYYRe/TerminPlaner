package ch.informatik.m223.TerminPlaner.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer roomId;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(length = 200, nullable = false)
    private String remark;

    @ElementCollection
    @CollectionTable(name = "reservation_participants", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "participant", nullable = false)
    private List<String> participants;

    @Column(nullable = false, unique = true)
    private String publicCode;

    @Column(nullable = false, unique = true)
    private String privateCode;

    // getters/setters …
}