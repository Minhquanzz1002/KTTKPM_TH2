package vn.edu.iuh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
