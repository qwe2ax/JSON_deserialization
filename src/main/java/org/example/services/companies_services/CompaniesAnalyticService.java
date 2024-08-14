package org.example.services.companies_services;

import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;
import org.example.entities.interfaces.Identifiable;
import org.example.services.interfaces.AnalyticService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CompaniesAnalyticService implements AnalyticService<Company> {

    @Override
    public int getAvgProfit(List<Company> companies) {
        return companies.stream().mapToInt(company -> Integer.parseInt(company.getProfit())).sum() / companies.size();
    }

    @Override
    public long getAvgLifetime(List<Company> companies, List<CloseInfoItem> closeInfoItems) {
        Map<String, LocalDateTime> companyCreationDates = companies.stream()
                /* Тут лист компаний преобразуется в мапу, мапа выглядит как String Id объекта, LocalDateTime дата создания объекта
                 * значением же является поле creationDate, которое парсится к LocalDate */
                .collect(Collectors.toMap(Identifiable::getId, company -> LocalDateTime.parse(company.getCreationDate())));
        long sumOfLifeTimeInDays = closeInfoItems.stream()
                /*    Тут список закрытых компаний фильтруется по наличию в нем ключа из мапы(Id)       */
                .filter(closedCompany -> companyCreationDates.containsKey(closedCompany.getId()))
                /* Тут каждый возвращенный элемент из операции кастится к лонгу, в операции(находится в фигурных скобках)
                 * из мапы берется значение по Id который является ключом и присваивается полю creationDate,
                 * после чего отфильтрованный элемент списка закрытых "компаний" парсится к LocalDate и присваивается полю closeDate
                 * после чего возвращается разность между ними в днях, метод sum() суммирует все полученные значения */
                .mapToLong(closedCompany -> {
                    LocalDateTime creationDate = companyCreationDates.get(closedCompany.getId());
                    LocalDateTime closeDate = LocalDateTime.parse(closedCompany.getCloseDate());
                    System.out.println(closeDate);
                    System.out.println(creationDate);
                    return Duration.between(creationDate, closeDate).toDays();
                })
                .sum();
//        long sumOfLifeTimeInDays = 0;
//        for (CloseInfoItemImpl closedCompany : closeInfoItems) {
//            if (companyCreationDates.containsKey(closedCompany.getId())) {
//                LocalDateTime creationDate = companyCreationDates.get(closedCompany.getId());
//                LocalDateTime closeDate = LocalDateTime.parse(closedCompany.getCloseDate());
//                System.out.println(creationDate);
//                System.out.println(closeDate);
//                long daysBetween = Duration.between(creationDate, closeDate).toDays();
//                sumOfLifeTimeInDays += daysBetween;
//            }
//        }

                /*
                Тут в очередной раз фильтруется список закрытых компаний по тому есть ли ключ равный Id выбранного объекта
                 */
        long count = closeInfoItems.stream()
                .filter(closedCompany -> companyCreationDates.containsKey(closedCompany.getId()))
                .count();
        /*
        Ну а тут ваще хуйня епта)).
        Таки надумал вернуть 0, если каунт равен нулю, как бы не выглядело это всрато, ведь я знаю, что он никак не будет меньше единицы
        правильнее конечно было бы добавить условный оператор в начало метода, но прямо сейчас мне похуй =)
        */
        return count == 0 ? 0 : sumOfLifeTimeInDays / count;
    }
}
