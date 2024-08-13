package org.example.services;

import org.example.entities.interfaces.Creatable;
import org.example.entities.interfaces.Identifiable;
import org.example.entities.interfaces.Profitable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.services.InitService.*;

public class AnalyticService {

    public static void printAnalytic() {
        int companiesProfit = getAvgProfit(companies);
        int entrepreneursProfit = getAvgProfit(entrepreneurs);
        int closedCompaniesProfit = getAvgProfit(closedCompanies);
        int closedEntrepreneursProfit = getAvgProfit(closedEntrepreneurs);
        long avgLifetimeForCompanies = getAvgLifeTime(closedCompanies);
        long avgLifetimeForEntrepreneurs = getAvgLifeTime(closedEntrepreneurs);
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

    public static <T extends Profitable> int getAvgProfit(List<T> companies) {
//        System.out.println(companies.size());
        return companies.stream().mapToInt(company -> Integer.parseInt(company.getProfit())).sum() / companies.size();
    }

    public static <T extends Identifiable & Creatable> long getAvgLifeTime(List<T> companies) {
        Map<String, LocalDateTime> companyCreationDates = companies.stream()
                /* Тут лист компаний преобразуется в мапу, мапа выглядит как String Id объекта, дата создания объекта
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
