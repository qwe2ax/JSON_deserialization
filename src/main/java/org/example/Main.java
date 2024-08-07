package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;
import org.example.services.DataService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String entrepreneursPath = "entrepreneurs.json";
        String companiesPath = "companies.json";
        String closedPath = "closeinfo.json";


        List<Entrepreneur> entrepreneurs = DataService.getData(entrepreneursPath, new TypeReference<Map<String, List<Entrepreneur>>>() {});
        entrepreneurs.forEach(System.out::println);
        System.out.println("\n\n");
        List<Company> companies = DataService.getData(companiesPath, new TypeReference<Map<String, List<Company>>>() {});;
        companies.forEach(System.out::println);
        System.out.println("\n\n");
        List<CloseInfoItem> closeInfoItems = DataService.getData(closedPath, new TypeReference<Map<String, List<CloseInfoItem>>>() {});
        closeInfoItems.forEach(System.out::println);

    }
}