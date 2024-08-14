package org.example;


import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;
import org.example.entities.implementations.Entrepreneur;
import org.example.services.DataService;
import org.example.services.ReportService;
import org.example.services.companies_services.CompaniesDataFilter;
import org.example.services.entrepreneurs_services.EntrepreneursDataFilter;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    static final String ENTREPRENEURS_JSON = "entrepreneurs.json";
    static final String COMPANIES_JSON = "companies.json";
    static final String CLOSEINFO_JSON = "closeinfo.json";

    public static void main(String[] args) {
        DataService dataService = new DataService();
        CompaniesDataFilter cdf = new CompaniesDataFilter();
        EntrepreneursDataFilter edf = new EntrepreneursDataFilter();
        List<CloseInfoItem> closeInfoItems = dataService.getData(CLOSEINFO_JSON, new TypeReference<>() {});
        Set<String> closedIds = dataService.getClosedIds(closeInfoItems);
        List<Company> companies = dataService.getData(COMPANIES_JSON, new TypeReference<>() {});
        List<Entrepreneur> entrepreneurs = dataService.getData(ENTREPRENEURS_JSON, new TypeReference<>() {});
        companies = cdf.removeDuplicateData(companies);
        entrepreneurs = edf.removeDuplicateData(entrepreneurs);
        List<Company> closedCompanies = cdf.transferInactiveData(companies, closedIds);
        List<Entrepreneur> closedEntrepreneurs = edf.transferInactiveData(entrepreneurs, closedIds);
        companies = cdf.removeInactiveData(companies, closedIds);
        entrepreneurs = edf.removeInactiveData(entrepreneurs, closedIds);



        ReportService.printAnalytic(companies, entrepreneurs, closedCompanies, closedEntrepreneurs, closeInfoItems);
    }
}