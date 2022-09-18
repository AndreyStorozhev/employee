package accounting.system.employee.repository;

import accounting.system.employee.entity.Vacation;
import org.springframework.data.repository.CrudRepository;

public interface VacationRepository extends CrudRepository<Vacation, Integer> {
}
