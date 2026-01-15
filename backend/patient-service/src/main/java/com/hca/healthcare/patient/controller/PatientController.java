package com.hca.healthcare.patient.controller;

import com.hca.healthcare.patient.entity.Patient;
import com.hca.healthcare.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PatientController {
    
    private final PatientService service;
    
    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        return new ResponseEntity<>(service.createPatient(patient), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Patient> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getPatient(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(service.getAllPatients());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable UUID id, @RequestBody Patient patient) {
        return ResponseEntity.ok(service.updatePatient(id, patient));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}