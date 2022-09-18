package accounting.system.employee.service.employee;

import accounting.system.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void saveNewEmployee(Employee employee);
    Employee findByLogin(String login);
    Employee findById(int id);
    List<Employee> findAllByName(String name);
    void updateEmployee(Employee employee);
}
