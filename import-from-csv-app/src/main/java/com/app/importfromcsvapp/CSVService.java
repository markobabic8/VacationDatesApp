package com.app.importfromcsvapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CSVService {
    @Autowired
    EmployeeVacationDaysRepository employeeVacationDaysRepository;

    @Autowired
    UsedVacationDatesRepository usedVacationDatesRepository;

    public void save(MultipartFile file) {
        try {
            if (file.getOriginalFilename() != null) {
                String year = file.getOriginalFilename().replaceAll("[^0-9]", "");
                if (!year.isEmpty()) { // TODO: Find better solution
                    List<EmployeeVacationDays> employeeVacationDaysList = CSVHelper.csvToEmployeeVacationDaysList(file.getInputStream(), Integer.parseInt(year));
                    employeeVacationDaysRepository.saveAll(employeeVacationDaysList);
                } else {
                    List<UsedVacationDates> usedVacationDatesList = CSVHelper.csvToUsedVacationDatesList(file.getInputStream());
                    usedVacationDatesRepository.saveAll(usedVacationDatesList);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to store CSV data " + e.getMessage());
        }
    }
}
