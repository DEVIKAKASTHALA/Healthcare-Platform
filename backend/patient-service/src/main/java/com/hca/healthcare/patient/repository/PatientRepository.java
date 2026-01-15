package com.hca.healthcare.patient.repository;

import com.hca.healthcare.patient.entity.Patient;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface PatientRepository extends CassandraRepository<Patient, UUID> {
    List<Patient> findByLastName(String lastName);
    Patient findByEmail(String email);
}