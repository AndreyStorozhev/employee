package accounting.system.employee.service.vacation;

import accounting.system.employee.entity.Vacation;

import java.util.Date;

public interface VacationService {
    Vacation createNewVacation(Date startWorkDay);
    Vacation findById(int id);
    void bookingVacation(Vacation vacation);
    void processVacation(Vacation vacation);
}
