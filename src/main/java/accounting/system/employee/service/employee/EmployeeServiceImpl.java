package accounting.system.employee.service.employee;

import accounting.system.employee.entity.Employee;
import accounting.system.employee.repository.EmployeeRepository;
import accounting.system.employee.service.vacation.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final VacationService vacationService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, BCryptPasswordEncoder encoder, VacationService vacationService) {
        this.repository = repository;
        this.encoder = encoder;
        this.vacationService = vacationService;
    }

    @Override
    public void saveNewEmployee(Employee employee) {
        employee.setVacation(vacationService.createNewVacation(employee.getStartWorkDay()));
        employee.setPassword(encoder.encode(employee.getPassword()));
        repository.save(employee);
    }

    @Override
    public Employee findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Employee findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Employee> findAllByName(String name) {
        return repository.findAllByNameLike("%" + name + "%");
    }

    @Override
    public void updateEmployee(Employee employee) {
        repository.save(employee);
    }
}
