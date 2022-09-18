package accounting.system.employee.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "vacation")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_vacation")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startVacation;

    @Column(name = "end_vacation")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endVacation;

    @Column(name = "count_holiday_day")
    private int countHolidayDay;

    @Column(name = "next_vacation_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextVacationDate;

    @OneToOne(mappedBy = "vacation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartVacation() {
        return startVacation;
    }

    public void setStartVacation(Date startVacation) {
        this.startVacation = startVacation;
    }

    public Date getEndVacation() {
        return endVacation;
    }

    public void setEndVacation(Date endVacation) {
        this.endVacation = endVacation;
    }

    public int getCountHolidayDay() {
        return countHolidayDay;
    }

    public void setCountHolidayDay(int countHolidayDay) {
        this.countHolidayDay = countHolidayDay;
    }

    public Date getNextVacationDate() {
        return nextVacationDate;
    }

    public void setNextVacationDate(Date nextVacationDate) {
        this.nextVacationDate = nextVacationDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation vacation = (Vacation) o;
        return id == vacation.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "id=" + id +
                ", startVacation=" + startVacation +
                ", endVacation=" + endVacation +
                ", countHolidayDay=" + countHolidayDay +
                ", nextVacationDate=" + nextVacationDate +
                '}';
    }
}
