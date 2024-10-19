package org.example.entities;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class AnalyticResponseDTO {
    private int activeCompanies;
    private int activeEntrepreneurs;
    private int closedCompanies;
    private int closedEntrepreneurs;
    private int avgProfitActiveCompanies;
    private int avgProfitActiveEntrepreneurs;
    private int avgProfitClosedCompanies;
    private int avgProfitClosedEntrepreneurs;
    private long avgLifetimeCompanies;
    private long avgLifetimeEntrepreneurs;

}
