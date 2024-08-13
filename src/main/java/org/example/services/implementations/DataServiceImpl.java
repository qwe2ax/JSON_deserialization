package org.example.services.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.example.entities.interfaces.DateComparable;
import org.example.entities.interfaces.Identifiable;
import org.example.entities.interfaces.Reportable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.services.InitService.*;

public class DataServiceImpl {

    public static <T> List<T> getData(String path, TypeReference<Map<String, List<T>>> typeReference) {
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

    public static <T extends DateComparable & Identifiable & Reportable> List<T> removeDuplicateData(List<T> companies) {
        return companies.stream()
                .collect(Collectors.groupingBy(T::getId))
                .values().stream()
                .map(l -> l.stream().max(T::compareDate).orElseThrow()).toList();
    }

    public static void removeInactiveData() {
        closedCompanies = companies.stream()
                .filter(c -> closedIds.contains(c.getId()))
                .toList();
        companies = companies.stream()
                .filter(c -> !closedIds.contains(c.getId()))
                .toList();
        closedEntrepreneurs = entrepreneurs.stream()
                .filter(c -> closedIds.contains(c.getId()))
                .toList();
        entrepreneurs = entrepreneurs.stream()
                .filter(e -> !closedIds.contains(e.getId()))
                .toList();
    }
}
