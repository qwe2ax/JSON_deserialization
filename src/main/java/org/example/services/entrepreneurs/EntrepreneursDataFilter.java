package org.example.services.entrepreneurs;

import org.example.entities.implementations.Entrepreneur;
import org.example.services.interfaces.DataFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EntrepreneursDataFilter implements DataFilter<Entrepreneur> {
    @Override
    public List<Entrepreneur> removeDuplicateData(List<Entrepreneur> entrepreneurs) {
        return entrepreneurs.stream()
                .collect(Collectors.groupingBy(Entrepreneur::getId))
                .values().stream()
                .map(l -> l.stream().max(Entrepreneur::compareDate).orElseThrow()).toList();
    }

    @Override
    public List<Entrepreneur> transferInactiveData(List<Entrepreneur> entrepreneurs, Set<String> closedIds) {
        return entrepreneurs.stream()
                .filter(c -> closedIds.contains(c.getId()))
                .toList();
    }

    @Override
    public List<Entrepreneur> removeInactiveData(List<Entrepreneur> entrepreneurs, Set<String> closedIds) {
        return entrepreneurs.stream()
                .filter(c -> !closedIds.contains(c.getId()))
                .toList();
    }
}
