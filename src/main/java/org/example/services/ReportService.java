package org.example.services;

import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;
import org.example.entities.implementations.Entrepreneur;
import org.example.services.companies.CompaniesAnalyticService;
import org.example.services.companies.CompaniesDataFilter;
import org.example.services.entrepreneurs.EntrepreneursAnalyticService;
import org.example.services.entrepreneurs.EntrepreneursDataFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReportService {

    private final List<Company> companies;
    private final List<Entrepreneur> entrepreneurs;
    private final List<CloseInfoItem> closeInfoItems;
    private final CompaniesDataFilter companiesDataFilter;
    private final EntrepreneursDataFilter entrepreneursDataFilter;
    private List<Company> filteredCompanies;
    private List<Entrepreneur> filteredEntrepreneurs;
    private List<Company> closedCompanies;
    private List<Entrepreneur> closedEntrepreneurs;

    @Autowired
    public ReportService(List<Company> companies, List<Entrepreneur> entrepreneurs, List<CloseInfoItem> closeInfoItems,
                         CompaniesDataFilter companiesDataFilter, EntrepreneursDataFilter entrepreneursDataFilter) {
        this.companies = companies;
        this.entrepreneurs = entrepreneurs;
        this.closeInfoItems = closeInfoItems;
        this.companiesDataFilter = companiesDataFilter;
        this.entrepreneursDataFilter = entrepreneursDataFilter;
    }

    @EventListener(ContextRefreshedEvent.class)
    private void init() {
        Set<String> closedIds = closeInfoItems.stream()
                .map(CloseInfoItem::getId)
                .collect(Collectors.toSet());

        List<Company> uniqueCompanies = companiesDataFilter.removeDuplicateData(companies);
        List<Entrepreneur> uniqueEntrepreneurs = entrepreneursDataFilter.removeDuplicateData(entrepreneurs);

        this.filteredCompanies = companiesDataFilter.removeInactiveData(uniqueCompanies, closedIds);
        this.closedCompanies = companiesDataFilter.transferInactiveData(uniqueCompanies, closedIds);
        this.filteredEntrepreneurs = entrepreneursDataFilter.removeInactiveData(uniqueEntrepreneurs, closedIds);
        this.closedEntrepreneurs = entrepreneursDataFilter.transferInactiveData(uniqueEntrepreneurs, closedIds);
    }

    public void printAnalytic(AnnotationConfigApplicationContext context) {
        CompaniesAnalyticService cas = context.getBean("companiesAnalyticService", CompaniesAnalyticService.class);
        EntrepreneursAnalyticService eas = context.getBean("entrepreneursAnalyticService", EntrepreneursAnalyticService.class);
        int companiesProfit = cas.getAvgProfit(filteredCompanies);
        int entrepreneursProfit = eas.getAvgProfit(filteredEntrepreneurs);
        int closedCompaniesProfit = cas.getAvgProfit(closedCompanies);
        int closedEntrepreneursProfit = eas.getAvgProfit(closedEntrepreneurs);
        long avgLifetimeForCompanies = cas.getAvgLifetime(closedCompanies, closeInfoItems);
        long avgLifetimeForEntrepreneurs = eas.getAvgLifetime(closedEntrepreneurs, closeInfoItems);
        System.out.println("Analytical Information for Individual Entrepreneurs and Companies:\n");
        System.out.printf("""
                        Number of active companies: %d
                        Number of active individual entrepreneurs: %d
                        Number of closed companies: %d
                        Number of closed individual entrepreneurs: %d
                        Average profit of active companies: %d RUB
                        Average profit of active individual entrepreneurs: %d RUB
                        Average profit of closed companies: %d RUB
                        Average profit of closed individual entrepreneurs: %d RUB
                        Average lifespan of companies (in days): %d
                        Average lifespan of individual entrepreneurs (in days): %d
                        """,
                filteredCompanies.size(), filteredEntrepreneurs.size(),
                closedCompanies.size(), closedEntrepreneurs.size(),
                companiesProfit, entrepreneursProfit, closedCompaniesProfit, closedEntrepreneursProfit,
                avgLifetimeForCompanies, avgLifetimeForEntrepreneurs);


    }
}