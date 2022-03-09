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
import java.sql.Date;
import java.util.*;

public class CSVHelper {
    private static final String TYPE = "text/csv";
    private static int employeeVacationDaysId = 1;
    private static int usedVacationDatesId = 1;

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<EmployeeVacationDays> csvToEmployeeVacationDaysList(InputStream is, int year) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            fileReader.readLine();
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<EmployeeVacationDays> employeeVacationDaysList = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser.getRecords()){
                EmployeeVacationDays employeeVacationDays = new EmployeeVacationDays(
                        employeeVacationDaysId++,
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

    public static List<UsedVacationDates> csvToUsedVacationDatesList(InputStream is) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<UsedVacationDates> usedVacationDatesList = new ArrayList<>();

            Map<String, String> months = new HashMap<>();
            months.put("January", "1");
            months.put("February", "2");
            months.put("March", "3");
            months.put("April", "4");
            months.put("May", "5");
            months.put("June", "6");
            months.put("July", "7");
            months.put("August", "8");
            months.put("September", "9");
            months.put("October", "10");
            months.put("November", "11");
            months.put("December", "12");

            for (CSVRecord csvRecord : csvParser.getRecords()){

                // TODO: find better solution
                String startDateFromCSV = csvRecord.get("Vacation start date");
                String[] startDateDetails = startDateFromCSV.split(", ");
                String[] startMonthAndDay = startDateDetails[1].split(" ");
                String month = months.get(startMonthAndDay[0]);
                String day = startMonthAndDay[1];
                String year = startDateDetails[2];
                String startDate = year + "-" + month + "-" + day;

                String endDateFromCSV = csvRecord.get("Vacation end date");
                String[] endDateDetails = endDateFromCSV.split(", ");
                String[] endMonthAndDay = endDateDetails[1].split(" ");
                month = months.get(endMonthAndDay[0]);
                day = endMonthAndDay[1];
                year = endDateDetails[2];
                String endDate = year + "-" + month + "-" + day;

                UsedVacationDates usedVacationDates = new UsedVacationDates(
                        usedVacationDatesId++,
                        csvRecord.get("Employee"),
                        Date.valueOf(startDate),
                        Date.valueOf(endDate)
                );
                usedVacationDatesList.add(usedVacationDates);
            }
            return usedVacationDatesList;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}
