package ch.informatik.m223.TerminPlaner.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(
        name = "room",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_room_name", columnNames = "name")
        }
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private Integer capacity;
}