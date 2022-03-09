package com.app.importfromcsvapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_vacation_days")
public class EmployeeVacationDays {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "employee")
    private String employee;

    @Column(name = "total_vacation_days")
    private int totalVacationDays;

    @Column(name = "year")
    private int year;

    public EmployeeVacationDays() {
    }

    public EmployeeVacationDays(long id, String employee, int totalVacationDays, int year) {
        this.id = id;
        this.employee = employee;
        this.totalVacationDays = totalVacationDays;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public String getEmployee() {
        return employee;
    }

    public int getTotalVacationDays() {
        return totalVacationDays;
    }

    public int getYear() {
        return year;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setTotalVacationDays(int totalVacationDays) {
        this.totalVacationDays = totalVacationDays;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "EmployeeVacationDays: [" +
                "id=" + id +
                ", employee='" + employee + '\'' +
                ", totalVacationDays=" + totalVacationDays +
                ", year=" + year +
                ']';
    }
}
