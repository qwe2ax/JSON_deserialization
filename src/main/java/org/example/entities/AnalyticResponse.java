package org.example.entities;

import lombok.Getter;

@Getter
public class AnalyticResponse {
    private final int activeCompanies;
    private final int activeEntrepreneurs;
    private final int closedCompanies;
    private final int closedEntrepreneurs;
    private final int avgProfitActiveCompanies;
    private final int avgProfitActiveEntrepreneurs;
    private final int avgProfitClosedCompanies;
    private final int avgProfitClosedEntrepreneurs;
    private final long avgLifetimeCompanies;
    private final long avgLifetimeEntrepreneurs;

    public AnalyticResponse(int activeCompanies, int activeEntrepreneurs, int closedCompanies, int closedEntrepreneurs,
                            int avgProfitActiveCompanies, int avgProfitActiveEntrepreneurs, int avgProfitClosedCompanies,
                            int avgProfitClosedEntrepreneurs, long avgLifetimeCompanies, long avgLifetimeEntrepreneurs) {
        this.activeCompanies = activeCompanies;
        this.activeEntrepreneurs = activeEntrepreneurs;
        this.closedCompanies = closedCompanies;
        this.closedEntrepreneurs = closedEntrepreneurs;
        this.avgProfitActiveCompanies = avgProfitActiveCompanies;
        this.avgProfitActiveEntrepreneurs = avgProfitActiveEntrepreneurs;
        this.avgProfitClosedCompanies = avgProfitClosedCompanies;
        this.avgProfitClosedEntrepreneurs = avgProfitClosedEntrepreneurs;
        this.avgLifetimeCompanies = avgLifetimeCompanies;
        this.avgLifetimeEntrepreneurs = avgLifetimeEntrepreneurs;
    }

}
