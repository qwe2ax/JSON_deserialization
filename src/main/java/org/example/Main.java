package org.example;

import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;
import org.example.services.DataService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Entrepreneur> entrepreneurs = DataService.getEntrepreneurs();
        entrepreneurs.forEach(System.out::println);
        System.out.println("\n\n");
        List<Company> companies = DataService.getCompanies();
        companies.forEach(System.out::println);
        System.out.println("\n\n");
        List<CloseInfoItem> closeInfoItems = DataService.getCloseInfoItems();
        closeInfoItems.forEach(System.out::println);

    }
}