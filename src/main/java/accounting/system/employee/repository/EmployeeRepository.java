package accounting.system.employee.repository;

import accounting.system.employee.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findByLogin(String login);
    List<Employee> findAllByNameLike(String name);
}
