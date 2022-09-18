package accounting.system.employee.service.vacation;

import accounting.system.employee.entity.Vacation;
import accounting.system.employee.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@Service
public class VacationServiceImpl implements VacationService {
    private final VacationRepository vacationRepository;

    @Autowired
    public VacationServiceImpl(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @Override
    public Vacation createNewVacation(Date startWorkDay) {
        Vacation vacation = new Vacation();
        vacation.setNextVacationDate(getHalfYear(startWorkDay));
        return vacation;
    }

    @Override
    public Vacation findById(int id) {
        return vacationRepository.findById(id).orElseThrow();
    }

    @Override
    public void bookingVacation(Vacation vacation) {
        long betweenDays = ChronoUnit.DAYS.between(vacation.getStartVacation().toInstant(),
                vacation.getEndVacation().toInstant());
        vacation.setCountHolidayDay((int) (vacation.getCountHolidayDay() - betweenDays));
        vacationRepository.save(vacation);
    }

    @Override
    public void processVacation(Vacation vacation) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(now);
        checkCountWeekendDays(vacation, now);
        if (vacation.getEndVacation() != null && now.after(vacation.getEndVacation())) {
            vacation.setStartVacation(null);
            vacation.setEndVacation(null);
        }
        vacationRepository.save(vacation);
    }

    private Date getHalfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 6);
        return calendar.getTime();
    }

    private void checkCountWeekendDays(Vacation vacation, Date now) {
        if (now.after(vacation.getNextVacationDate())) {
            vacation.setCountHolidayDay(vacation.getCountHolidayDay() + 14);
            vacation.setNextVacationDate(getHalfYear(vacation.getNextVacationDate()));
        }
    }
}
