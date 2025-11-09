package ch.informatik.m223.TerminPlaner.repository;

import ch.informatik.m223.TerminPlaner.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}