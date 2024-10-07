package org.example.services.companies;

import org.example.entities.Company;
import org.example.services.interfaces.DataFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CompaniesDataFilter implements DataFilter<Company> {

    @Override
    public List<Company> removeDuplicateData(List<Company> companies) {
        return companies.stream()
                .collect(Collectors.groupingBy(Company::getId))
                .values().stream()
                .map(l -> l.stream().max(Company::compareDate).orElseThrow()).toList();
    }

    @Override
    public List<Company> transferInactiveData(List<Company> companies, Set<String> closedIds) {
        return companies.stream()
                .filter(c -> closedIds.contains(c.getId()))
                .toList();
    }

    @Override
    public List<Company> removeInactiveData(List<Company> companies, Set<String> closedIds) {
        return companies.stream()
                .filter(c -> !closedIds.contains(c.getId()))
                .toList();
    }
}
