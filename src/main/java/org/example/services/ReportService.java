package org.example.services;

import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;
import org.example.entities.implementations.Entrepreneur;
import org.example.services.companies_services.CompaniesAnalyticService;
import org.example.services.entrepreneurs_services.EntrepreneursAnalyticService;

import java.util.List;


public class ReportService {
    public static void printAnalytic(List<Company> companies, List<Entrepreneur> entrepreneurs, List<Company> closedCompanies,
                                     List<Entrepreneur> closedEntrepreneurs, List<CloseInfoItem> closeInfoItems) {
        CompaniesAnalyticService cas = new CompaniesAnalyticService();
        EntrepreneursAnalyticService eas = new EntrepreneursAnalyticService();
        int companiesProfit = cas.getAvgProfit(companies);
        int entrepreneursProfit = eas.getAvgProfit(entrepreneurs);
        int closedCompaniesProfit = cas.getAvgProfit(closedCompanies);
        int closedEntrepreneursProfit = eas.getAvgProfit(closedEntrepreneurs);
        long avgLifetimeForCompanies = cas.getAvgLifetime(closedCompanies, closeInfoItems);
        long avgLifetimeForEntrepreneurs = eas.getAvgLifetime(closedEntrepreneurs, closeInfoItems);
        System.out.println("Аналитическая информация по ИП и Компаниям:\n ");
        System.out.printf("Количество действующих компаний: %d\n" +
                        "Количество действующих ИП: %d\n" +
                        "Количество закрытых компаний: %d\n" +
                        "Количество закрытых ИП: %d\n" +
                        "Средняя прибыль действующих компаний: %dр\n" +
                        "Средняя прибыль действующих ИП: %dр\n" +
                        "Средняя прибыль недействующих компаний: %dр\n" +
                        "Средняя прибыль недействующих ИП: %dр\n" +
                        "Среднее время существования компаний (в днях): %d\n" +
                        "Среднее время существования ИП (в днях): %d\n",
                companies.size(), entrepreneurs.size(),
                closedCompanies.size(), closedEntrepreneurs.size(),
                companiesProfit, entrepreneursProfit, closedCompaniesProfit, closedEntrepreneursProfit,
                avgLifetimeForCompanies, avgLifetimeForEntrepreneurs);

    }
}
