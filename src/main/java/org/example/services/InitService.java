package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;
import org.example.entities.implementations.Entrepreneur;
import org.example.services.implementations.DataServiceImpl;

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
        closeInfoItems = DataServiceImpl.getData(closedPath, new TypeReference<>() {
        });
        closedIds = closeInfoItems.stream()
                .map(CloseInfoItem::getId)
                .collect(Collectors.toSet());

        entrepreneurs = DataServiceImpl.getData(ENTREPRENEURS_PATH, new TypeReference<>() {
        });
        entrepreneurs = DataServiceImpl.removeDuplicateData(entrepreneurs);
        companies = DataServiceImpl.getData(companiesPath, new TypeReference<>() {
        });
        companies = DataServiceImpl.removeDuplicateData(companies);
        DataServiceImpl.removeInactiveData();
    }
}
