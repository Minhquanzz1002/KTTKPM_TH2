package vn.edu.iuh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.models.Examine;
import vn.edu.iuh.models.ExaminePK;

@Repository
public interface ExamineRepository extends JpaRepository<Examine, ExaminePK> {
}
