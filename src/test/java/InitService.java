import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;
import org.example.entities.implementations.Entrepreneur;
import org.example.services.DataService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.Main.*;

public class InitService {
//    static List<Entrepreneur> entrepreneurs;
//    static List<Company> companies;
//    static List<CloseInfoItem> closeInfoItems;
//    static Set<String> closedIds;
//    static List<Company> closedCompanies;
//    static List<Entrepreneur> closedEntrepreneurs;
//
//
//    public static void init() {
//        closeInfoItems = DataService.getData(closedPath, new TypeReference<>() {
//        });
//        closedIds = closeInfoItems.stream()
//                .map(CloseInfoItem::getId)
//                .collect(Collectors.toSet());
//
//        entrepreneurs = DataService.getData(ENTREPRENEURS_PATH, new TypeReference<>() {
//        });
//        entrepreneurs = DataService.removeDuplicateData(entrepreneurs);
//        companies = DataService.getData(companiesPath, new TypeReference<>() {
//        });
//        companies = DataService.removeDuplicateData(companies);
//        DataService.removeInactiveData();
//    }
}
