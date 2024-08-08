package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.Main.*;

public class InitService {
    public static List<Entrepreneur> entrepreneurs;
    public static List<Company> companies;
    public static List<CloseInfoItem> closeInfoItems;
    public static Set<String> closedIds;
    public static List<Company> closedCompanies;
    public static List<Entrepreneur> closedEntrepreneurs;


    public static void init() {
        closeInfoItems = DataService.getData(closedPath, new TypeReference<>() {
        });
        closedIds = closeInfoItems.stream()
                .map(CloseInfoItem::getId)
                .collect(Collectors.toSet());

        entrepreneurs = DataService.getData(entrepreneursPath, new TypeReference<>() {
        });
        entrepreneurs = DataService.removeDuplicateData(entrepreneurs);
        companies = DataService.getData(companiesPath, new TypeReference<>() {
        });
        companies = DataService.removeDuplicateData(companies);
        DataService.removeInactiveData();
    }
}
