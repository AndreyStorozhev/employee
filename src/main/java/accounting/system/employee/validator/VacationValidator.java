package accounting.system.employee.validator;

import accounting.system.employee.entity.Vacation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.temporal.ChronoUnit;

@Component
public class VacationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Vacation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Vacation vacation = (Vacation) target;
        long betweenDays = ChronoUnit.DAYS.between(vacation.getStartVacation().toInstant(), vacation.getEndVacation().toInstant());
        if (vacation.getCountHolidayDay() < betweenDays)
            errors.rejectValue("startVacation", "between.days.error");
    }
}
