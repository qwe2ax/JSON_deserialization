package org.example.services.interfaces;

import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;

import java.util.List;

public interface AnalyticService<T> {
    int getAvgProfit(List<T> entities);

    long getAvgLifetime(List<T> entities, List<CloseInfoItem> closeInfoItems);
}
