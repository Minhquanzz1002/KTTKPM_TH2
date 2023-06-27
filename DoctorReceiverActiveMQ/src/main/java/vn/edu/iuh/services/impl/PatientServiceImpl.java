package vn.edu.iuh.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.models.Patient;
import vn.edu.iuh.repositories.PatientRepository;
import vn.edu.iuh.services.PatientService;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
}
