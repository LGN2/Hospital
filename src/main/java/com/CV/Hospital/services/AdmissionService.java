package com.CV.Hospital.services;

import com.CV.Hospital.entities.Admission;
import com.CV.Hospital.entities.Patient;
import com.CV.Hospital.entities.Room;
import com.CV.Hospital.repositories.AdmissionRepository;
import com.CV.Hospital.repositories.PatientRepository;
import com.CV.Hospital.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionService {
    private final AdmissionRepository admissionRepository;
    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;

    public Admission admitPatient(
            Admission admission,
            Long patientId,
            Long roomId) {
        Patient patient =
                patientRepository.findByIdAndIsActiveTrue(patientId)
                        .orElseThrow(() ->
                                new RuntimeException("Patient not found"));
        Room room =
                roomRepository.findByIdAndIsActiveTrue(roomId)
                        .orElseThrow(() ->
                                new RuntimeException("Room not found"));
        List<Admission> currentAdmissions =
                admissionRepository
                        .findByRoomIdAndDischargeDateIsNullAndIsActiveTrue(roomId);
        if (currentAdmissions.size() >= room.getCapacity()) {
            throw new IllegalStateException(
                    "Room has reached maximum capacity"
            );
        }
        admission.setPatient(patient);
        admission.setRoom(room);
        admission.setIsActive(true);
        if (admission.getAdmitDate() == null) {
            admission.setAdmitDate(LocalDate.now());
        }
        return admissionRepository.save(admission);
    }

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findByIsActiveTrue();
    }

    public Admission getAdmissionById(Long id) {
        return admissionRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Admission not found"));
    }
}
