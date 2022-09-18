package accounting.system.employee.entity;

import accounting.system.employee.enums.Profession;
import accounting.system.employee.enums.Role;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    @Size(min = 4, max = 16, message = "{login.error}")
    private String login;

    @Column(name = "password")
    @Size(min = 6, message = "{password.error}")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "{name.error}")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "{surname.error}")
    private String surname;

    @Column(name = "middle_name")
    @NotEmpty(message = "{middleName.error}")
    private String middleName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "start_work_day")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startWorkDay;

    @Column(name = "profession")
    @Enumerated(EnumType.ORDINAL)
    private Profession profession;

    @Column(name = "salary")
    private int salary;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "skills")
    private String skills;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "phone")
    private String phone;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "photo")
    private byte[] photo;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "vacation_id", referencedColumnName = "id")
    private Vacation vacation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStartWorkDay() {
        return startWorkDay;
    }

    public void setStartWorkDay(Date startWorkDay) {
        this.startWorkDay = startWorkDay;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role=" + role +
                ", startWorkDay=" + startWorkDay +
                ", profession=" + profession +
                ", salary=" + salary +
                ", hobbies='" + hobbies + '\'' +
                ", skills='" + skills + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", vacation=" + vacation +
                '}';
    }
}