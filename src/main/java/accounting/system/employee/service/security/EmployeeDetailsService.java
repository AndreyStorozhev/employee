package accounting.system.employee.service.security;

import accounting.system.employee.entity.Employee;
import accounting.system.employee.repository.EmployeeRepository;
import accounting.system.employee.service.vacation.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository repository;
    private final VacationService vacationService;

    @Autowired
    public EmployeeDetailsService(EmployeeRepository repository, VacationService vacationService) {
        this.repository = repository;
        this.vacationService = vacationService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = repository.findByLogin(login);
        if (employee == null)
            throw new UsernameNotFoundException("Employee " + login + " not found");
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(employee.getRole().name()));
        vacationService.processVacation(employee.getVacation());
        return new User(employee.getLogin(), employee.getPassword(), grantedAuthorities);
    }
}
