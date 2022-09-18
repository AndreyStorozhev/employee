package accounting.system.employee.controller;

import accounting.system.employee.entity.Employee;
import accounting.system.employee.entity.Vacation;
import accounting.system.employee.service.employee.EmployeeService;
import accounting.system.employee.service.vacation.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VacationController {
    private final EmployeeService employeeService;
    private final VacationService vacationService;
    private final Validator validator;

    @Autowired
    public VacationController(EmployeeService employeeService, VacationService vacationService, @Qualifier("vacationValidator") Validator validator) {
        this.employeeService = employeeService;
        this.vacationService = vacationService;
        this.validator = validator;
    }

    @GetMapping("/vacation/booking")
    public String bookingVacation(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findByLogin(authentication.getName());
        model.addAttribute("vacation", employee.getVacation());
        return "bookingVacation";
    }

    @PostMapping ("/vacation/booking")
    public String bookingVacation(@ModelAttribute Vacation vacation, BindingResult result, Model model) {
        validator.validate(vacation, result);
        if (result.hasErrors()) {
            if (vacation.getCountHolidayDay() == 0)
                model.addAttribute("errorVacation", "Нет доступных отпускных дней");
            return "bookingVacation";
        }
        vacationService.bookingVacation(vacation);
        return "redirect:/";
    }
}
