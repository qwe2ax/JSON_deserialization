package org.example.services.implementations;

import lombok.RequiredArgsConstructor;
import org.example.entities.AnalyticResponseDTO;
import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;
import org.example.services.companies.CompaniesAnalyticService;
import org.example.services.companies.CompaniesDataFilter;
import org.example.services.entrepreneurs.EntrepreneursAnalyticService;
import org.example.services.entrepreneurs.EntrepreneursDataFilter;
import org.example.services.interfaces.ReportService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final List<Company> companies;
    private final List<Entrepreneur> entrepreneurs;
    private final List<CloseInfoItem> closeInfoItems;
    private final CompaniesDataFilter companiesDataFilter;
    private final EntrepreneursDataFilter entrepreneursDataFilter;
    private List<Company> filteredCompanies;
    private List<Entrepreneur> filteredEntrepreneurs;
    private List<Company> closedCompanies;
    private List<Entrepreneur> closedEntrepreneurs;

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

    public Map<String, Object> getStatistic() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("activeCompanies", filteredCompanies);
        stats.put("closedCompanies", closedCompanies);
        stats.put("activeEntrepreneurs", filteredEntrepreneurs);
        stats.put("closedEntrepreneurs", closedEntrepreneurs);
        return stats;
    }

    public AnalyticResponseDTO getAnalytic() {
        CompaniesAnalyticService cas = new CompaniesAnalyticService();
        EntrepreneursAnalyticService eas = new EntrepreneursAnalyticService();
        int companiesProfit = cas.getAvgProfit(filteredCompanies);
        int entrepreneursProfit = eas.getAvgProfit(filteredEntrepreneurs);
        int closedCompaniesProfit = cas.getAvgProfit(closedCompanies);
        int closedEntrepreneursProfit = eas.getAvgProfit(closedEntrepreneurs);
        long avgLifetimeForCompanies = cas.getAvgLifetime(closedCompanies, closeInfoItems);
        long avgLifetimeForEntrepreneurs = eas.getAvgLifetime(closedEntrepreneurs, closeInfoItems);

        AnalyticResponseDTO analyticResponseDTO = new AnalyticResponseDTO(filteredCompanies.size(), filteredEntrepreneurs.size(),
                closedCompanies.size(), closedEntrepreneurs.size(),
                companiesProfit, entrepreneursProfit, closedCompaniesProfit, closedEntrepreneursProfit,
                avgLifetimeForCompanies, avgLifetimeForEntrepreneurs);

        return analyticResponseDTO;

//        return String.format("""
//                        Number of active companies: %d
//                        Number of active individual entrepreneurs: %d
//                        Number of closed companies: %d
//                        Number of closed individual entrepreneurs: %d
//                        Average profit of active companies: %d RUB
//                        Average profit of active individual entrepreneurs: %d RUB
//                        Average profit of closed companies: %d RUB
//                        Average profit of closed individual entrepreneurs: %d RUB
//                        Average lifespan of companies (in days): %d
//                        Average lifespan of individual entrepreneurs (in days): %d
//                        """,

    }


}