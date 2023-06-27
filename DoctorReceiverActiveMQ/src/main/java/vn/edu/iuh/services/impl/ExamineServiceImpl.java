package vn.edu.iuh.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.models.Examine;
import vn.edu.iuh.repositories.ExamineRepository;
import vn.edu.iuh.services.ExamineService;

@Service
@AllArgsConstructor
public class ExamineServiceImpl implements ExamineService {
    private ExamineRepository examineRepository;
    @Override
    public Examine save(Examine examine) {
        return examineRepository.save(examine);
    }
}
