package com.CV.Hospital.repositories;

import com.CV.Hospital.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository
        extends JpaRepository<Room, Long> {

    List<Room> findByIsActiveTrue();

    Optional<Room> findByIdAndIsActiveTrue(Long id);

    List<Room> findByHospitalIdAndIsActiveTrue(
            Long hospitalId
    );

    @Query("""
            SELECT r
            FROM Room r
            WHERE r.isActive = true
            AND (
                SELECT COUNT(a)
                FROM Admission a
                WHERE a.room.id = r.id
                AND a.isActive = true
                AND a.dischargeDate IS NULL
            ) < r.capacity
            """)
    List<Room> findRoomsWithAvailableCapacity();

    @Query("""
            SELECT r
            FROM Room r
            WHERE r.hospital.id = :hospitalId
            AND r.isActive = true
            AND (
                SELECT COUNT(a)
                FROM Admission a
                WHERE a.room.id = r.id
                AND a.isActive = true
                AND a.dischargeDate IS NULL
            ) < r.capacity
            """)
    List<Room> findRoomsWithAvailableCapacityByHospital(
            @Param("hospitalId") Long hospitalId
    );
}