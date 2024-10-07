package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.example.entities.CloseInfoItem;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class DataService {

    public <T> List<T> getData(String path, TypeReference<Map<String, List<T>>> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream;
        Map<String, List<T>> map;
        try {
            inputStream = Resources.getResource(path).openStream();
            map = mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map.values().stream()
                .flatMap(Collection::stream)
                .toList();
    }

    public Set<String> getClosedIds(List<CloseInfoItem> closeInfoItems) {
        return closeInfoItems.stream()
                .map(CloseInfoItem::getId).collect(Collectors.toSet());
    }

//    public static <T extends DateComparable & Identifiable & Reportable> List<T> removeDuplicateData(List<T> companies) {
//        return companies.stream()
//                .collect(Collectors.groupingBy(T::getId))
//                .values().stream()
//                .map(l -> l.stream().max(T::compareDate).orElseThrow()).toList();
//    }
//
//    public static void removeInactiveData() {
//        closedCompanies = companies.stream()
//                .filter(c -> closedIds.contains(c.getId()))
//                .toList();
//        companies = companies.stream()
//                .filter(c -> !closedIds.contains(c.getId()))
//                .toList();
//        closedEntrepreneurs = entrepreneurs.stream()
//                .filter(c -> closedIds.contains(c.getId()))
//                .toList();
//        entrepreneurs = entrepreneurs.stream()
//                .filter(e -> !closedIds.contains(e.getId()))
//                .toList();
//    }
}
