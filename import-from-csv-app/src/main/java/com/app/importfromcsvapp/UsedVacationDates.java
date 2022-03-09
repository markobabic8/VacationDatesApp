package com.app.importfromcsvapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "used_vacation_dates")
public class UsedVacationDates {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "employee")
    private String employee;

    @Column(name = "vacation_start_date")
    private Date vacationStartDate;

    @Column(name = "vacation_end_date")
    private Date vacationEndDate;

    public UsedVacationDates() {
    }

    public UsedVacationDates(int id, String employee, Date vacationStartDate, Date vacationEndDate) {
        this.id = id;
        this.employee = employee;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
    }

    public int getId() {
        return id;
    }

    public String getEmployee() {
        return employee;
    }

    public Date getVacationStartDate() {
        return vacationStartDate;
    }

    public Date getVacationEndDate() {
        return vacationEndDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setVacationStartDate(Date vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public void setVacationEndDate(Date vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }
}
