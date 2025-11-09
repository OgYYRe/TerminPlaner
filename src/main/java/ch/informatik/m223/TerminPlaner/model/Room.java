package ch.informatik.m223.TerminPlaner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    private Integer id;

    // Getter und Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}