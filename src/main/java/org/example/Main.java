package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;
import org.example.services.AnalyticService;
import org.example.services.DataService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String entrepreneursPath = "entrepreneurs.json";
        String companiesPath = "companies.json";
        String closedPath = "closeinfo.json";
        List<CloseInfoItem> closeInfoItems = DataService.getData(closedPath, new TypeReference<>() {
        });
        Set<String> closedIds = closeInfoItems.stream()
                .map(CloseInfoItem::getId)
                .collect(Collectors.toSet());
//        closeInfoItems.forEach(System.out::println);


        List<Entrepreneur> entrepreneurs = DataService.getData(entrepreneursPath, new TypeReference<>() {
        });

        int size = entrepreneurs.size();

        entrepreneurs = DataService.getFilteredData(entrepreneurs, closedIds);
        int filteredSize = entrepreneurs.size();
        entrepreneurs.forEach(System.out::println);
        System.out.println(AnalyticService.getAvgProfit(entrepreneurs));




//        List<Company> companies = DataService.getData(companiesPath, new TypeReference<>() {
//        });
//        companies = AnalyticService.analytic(companies, closedIds);
//        companies.forEach(System.out::println);
//
//        System.out.println(AnalyticService.getAvgProfit(companies));

    }
}