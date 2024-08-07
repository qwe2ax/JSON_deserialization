package org.example.services;

import org.example.entities.CloseInfoItem;
import org.example.entities.Company;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalyticService {
    public static <T extends Company> int getAvgProfit(List<T> companies) {
        System.out.println(companies.size());
        return companies.stream().mapToInt(company -> Integer.parseInt(company.getProfit())).sum() / companies.size();
    }
}
