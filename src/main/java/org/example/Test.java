package org.example;

import org.example.entities.Company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        Company company = new Company();
        Company company1 = new Company();
        String dateTimeString = "2017-08-30T00:00:00";
        String dateTimeStringPrev = "2017-03-05T00:00:00";
        company.setReportDate(dateTimeString);
        company1.setReportDate(dateTimeStringPrev);

        System.out.println(company1.compareTo(company));
    }
}
