package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository
        extends JpaRepository<Room, Long> {
    List<Room> findByIsActiveTrue();
    Optional<Room> findByIdAndIsActiveTrue(Long id);
    List<Room> findByHospitalIdAndIsActiveTrue(Long hospitalId);
}