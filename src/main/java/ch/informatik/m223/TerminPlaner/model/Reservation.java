package ch.informatik.m223.TerminPlaner.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Wir brauchen eine Beziehung zur 'rooms' Tabelle
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @NotNull(message = "Bitte wählen Sie einen Raum aus.")
    private Room room;


    @Column(name = "start_at", nullable = false)
    @NotNull(message = "Bitte geben Sie ein Startdatum.")
    @FutureOrPresent(message = "Startzeit muss heute oder in der Zukunft liegen.")
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    @NotNull(message = "Bitte wählen Sie eine Endzeit.")
    private LocalDateTime endAt;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "Bitte geben Sie eine Bemerkung ein.")
    @Size(min=10, max=200, message = "Die Bemerkung muss zwischen 10 und 200 Zeichen lang sein.")
    private String remark;


    @Column(name = "public_code", nullable = false, unique = true, length = 32)
    private String publicCode;

    @Column(name = "private_code", nullable = false, unique = true, length = 32)
    private String privateCode;

    @Column(name = "participants", nullable = false, length = 200)
    @NotBlank(message = "Teilbehmer dürfen nicht leer sein.")
    @Pattern(regexp = "^[A-Za-zÄÖÜäöü]+ [A-Za-zÄÖÜäöü]+(?: [A-Za-zÄÖÜäöü]+)*(, [A-Za-zÄÖÜäöü]+ [A-Za-zÄÖÜäöü]+(?: [A-Za-zÄÖÜäöü]+)*)*$",
    message = "Teilnehmer müssen durch Kommas getrennt sein und dürfen nur Buchstaben und Leerzeichen enthalten.")
    private String participants;

    // Getter und Setter...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public LocalDateTime getStartAt() { return startAt; }
    public void setStartAt(LocalDateTime startAt) { this.startAt = startAt; }
    public LocalDateTime getEndAt() { return endAt; }
    public void setEndAt(LocalDateTime endAt) { this.endAt = endAt; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getParticipants() { return participants; }
    public void setParticipants(String participants) { this.participants = participants; }
    public String getPublicCode() { return publicCode; }
    public void setPublicCode(String publicCode) { this.publicCode = publicCode; }
    public String getPrivateCode() { return privateCode; }
    public void setPrivateCode(String privateCode) { this.privateCode = privateCode; }
}