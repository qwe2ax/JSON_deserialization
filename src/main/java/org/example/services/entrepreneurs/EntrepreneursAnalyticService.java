package org.example.services.entrepreneurs;

import org.example.entities.CloseInfoItem;
import org.example.entities.Entrepreneur;
import org.example.entities.interfaces.Identifiable;
import org.example.services.interfaces.AnalyticService;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EntrepreneursAnalyticService implements AnalyticService<Entrepreneur> {
    @Override
    public int getAvgProfit(List<Entrepreneur> entrepreneurs) {
        return entrepreneurs.stream().mapToInt(company -> Integer.parseInt(company.getProfit())).sum() / entrepreneurs.size();
    }

    @Override
    public long getAvgLifetime(List<Entrepreneur> entrepreneurs, List<CloseInfoItem> closeInfoItems) {
        Map<String, LocalDateTime> companyCreationDates = entrepreneurs.stream()
                .collect(Collectors.toMap(Identifiable::getId, company -> LocalDateTime.parse(company.getCreationDate())));
        long sumOfLifeTimeInDays = closeInfoItems.stream()
                .filter(closedCompany -> companyCreationDates.containsKey(closedCompany.getId()))
                .mapToLong(closedCompany -> {
                    LocalDateTime creationDate = companyCreationDates.get(closedCompany.getId());
                    LocalDateTime closeDate = LocalDateTime.parse(closedCompany.getCloseDate());
                    System.out.println(closeDate);
                    System.out.println(creationDate);
                    return Duration.between(creationDate, closeDate).toDays();
                })
                .sum();
        long count = closeInfoItems.stream()
                .filter(closedCompany -> companyCreationDates.containsKey(closedCompany.getId()))
                .count();
        return count == 0 ? 0 : sumOfLifeTimeInDays / count;
    }
}
