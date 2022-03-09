package com.app.importfromcsvapp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    private static final String TYPE = "text/csv";
    private static int id = 1;

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<EmployeeVacationDays> csvToEmployeeVacationDayList(InputStream is, int year) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            fileReader.readLine();
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<EmployeeVacationDays> employeeVacationDaysList = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser.getRecords()){
                EmployeeVacationDays employeeVacationDays = new EmployeeVacationDays(
                        id++,
                        csvRecord.get("Employee"),
                        Integer.parseInt(csvRecord.get("Total vacation days")),
                        year
                );
                employeeVacationDaysList.add(employeeVacationDays);
            }
            return employeeVacationDaysList;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}
