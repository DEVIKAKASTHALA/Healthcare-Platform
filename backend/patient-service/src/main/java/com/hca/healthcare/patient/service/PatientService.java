package com.hca.healthcare.patient.service;

import com.hca.healthcare.patient.entity.Patient;
import com.hca.healthcare.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {
    
    private final PatientRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public Patient createPatient(Patient patient) {
        patient.setPatientId(UUID.randomUUID());
        patient.setRegistrationDate(LocalDate.now());
        Patient saved = repository.save(patient);
        
        kafkaTemplate.send("patient-events", 
            "Patient Created: " + saved.getPatientId());
        log.info("Created patient: {}", saved.getPatientId());
        
        return saved;
    }
    
    public Patient getPatient(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found: " + id));
    }
    
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
    
    public Patient updatePatient(UUID id, Patient patient) {
        Patient existing = getPatient(id);
        existing.setFirstName(patient.getFirstName());
        existing.setLastName(patient.getLastName());
        existing.setEmail(patient.getEmail());
        existing.setPhone(patient.getPhone());
        existing.setAddress(patient.getAddress());
        
        Patient updated = repository.save(existing);
        kafkaTemplate.send("patient-events", 
            "Patient Updated: " + id);
        
        return updated;
    }
    
    public void deletePatient(UUID id) {
        repository.deleteById(id);
        kafkaTemplate.send("patient-events", 
            "Patient Deleted: " + id);
        log.info("Deleted patient: {}", id);
    }
}