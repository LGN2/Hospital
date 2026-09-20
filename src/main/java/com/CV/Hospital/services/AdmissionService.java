package com.CV.Hospital.services;

import com.CV.Hospital.exceptions.BadRequestException;
import com.CV.Hospital.exceptions.ResourceNotFoundException;
import com.CV.Hospital.dto.AdmissionDTO;
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

    public AdmissionDTO admitPatient(AdmissionDTO dto) {

        Patient patient = getPatient(dto.getPatientId());
        Room room = getRoom(dto.getRoomId());

        checkRoomCapacity(room);

        Admission admission = new Admission();

        admission.setPatient(patient);
        admission.setRoom(room);
        admission.setAdmitDate(
                dto.getAdmitDate() == null
                        ? LocalDate.now()
                        : dto.getAdmitDate()
        );

        admission.setDischargeDate(null);

        return convertToDTO(
                admissionRepository.save(admission)
        );
    }

    public List<AdmissionDTO> getAllAdmissions() {
        return convertToDTO(
                admissionRepository.findByIsActiveTrue()
        );
    }

    public AdmissionDTO getAdmissionById(Long id) {
        return convertToDTO(findActiveAdmission(id));
    }

    public List<AdmissionDTO> getAdmissionsByPatient(
            Long patientId) {

        return convertToDTO(
                admissionRepository
                        .findByPatientIdAndIsActiveTrue(patientId)
        );
    }

    public List<AdmissionDTO> getAdmissionsByRoom(Long roomId) {
        return convertToDTO(
                admissionRepository
                        .findByRoomIdAndIsActiveTrue(roomId)
        );
    }

    public AdmissionDTO updateAdmission(
            Long id,
            AdmissionDTO dto) {

        Admission admission = findActiveAdmission(id);

        Patient patient = getPatient(dto.getPatientId());
        Room room = getRoom(dto.getRoomId());

        if (!admission.getRoom().getId().equals(room.getId())) {
            checkRoomCapacity(room);
        }

        if (dto.getDischargeDate() != null
                && dto.getDischargeDate().isBefore(dto.getAdmitDate())) {
            throw new BadRequestException("Discharge date cannot be before admission date");
        }

        if (dto.getDischargeDate() != null && dto.getDischargeDate().isBefore(dto.getAdmitDate())) {
            throw new BadRequestException("Discharge date cannot be before admission date");
        }

        admission.setPatient(patient);
        admission.setRoom(room);
        admission.setAdmitDate(dto.getAdmitDate());
        admission.setDischargeDate(dto.getDischargeDate());

        return convertToDTO(
                admissionRepository.save(admission)
        );
    }

    public AdmissionDTO dischargePatient(Long id) {

        Admission admission = findActiveAdmission(id);

        if (admission.getDischargeDate() != null) {
            throw new BadRequestException(
                    "Patient is already discharged"
            );
        }

        admission.setDischargeDate(LocalDate.now());

        return convertToDTO(
                admissionRepository.save(admission)
        );
    }

    public void deleteAdmission(Long id) {
        Admission admission = findActiveAdmission(id);
        admission.setIsActive(false);
        admissionRepository.save(admission);
    }

    private void checkRoomCapacity(Room room) {

        int currentPatients =
                admissionRepository
                        .findByRoomIdAndDischargeDateIsNullAndIsActiveTrue(
                                room.getId()
                        )
                        .size();

        if (currentPatients >= room.getCapacity()) {
            throw new BadRequestException(
                    "Room has reached maximum capacity"
            );
        }
    }

    private Admission findActiveAdmission(Long id) {
        return admissionRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admission not found"));
    }

    private Patient getPatient(Long id) {
        return patientRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found"));
    }

    private Room getRoom(Long id) {
        return roomRepository
                .findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));
    }

    public AdmissionDTO convertToDTO(Admission admission) {
        return AdmissionDTO.builder()
                .id(admission.getId())
                .admitDate(admission.getAdmitDate())
                .dischargeDate(admission.getDischargeDate())
                .patientId(admission.getPatient().getId())
                .roomId(admission.getRoom().getId())
                .build();
    }

    public List<AdmissionDTO> convertToDTO(
            List<Admission> admissions) {

        return admissions.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<AdmissionDTO> getPatientsAdmittedToFloor(
            Integer floor) {

        return convertToDTO(
                admissionRepository
                        .findActiveAdmissionsByFloor(floor)
        );
    }
}