package com.app.importfromcsvapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CSVService {
    @Autowired
    EmployeeVacationDaysRepository repository;

    public void save(MultipartFile file) {
        try {
            if (file.getOriginalFilename() != null) {
                String year = file.getOriginalFilename().replaceAll("[^0-9]", "");
                List<EmployeeVacationDays> employeeVacationDaysList = CSVHelper.csvToEmployeeVacationDayList(file.getInputStream(), Integer.parseInt(year));
                repository.saveAll(employeeVacationDaysList);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to store CSV data " + e.getMessage());
        }
    }
}
