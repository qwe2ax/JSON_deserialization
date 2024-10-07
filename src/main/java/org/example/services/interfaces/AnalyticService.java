package org.example.services.interfaces;

import org.example.entities.CloseInfoItem;

import java.util.List;

public interface AnalyticService<T> {
    int getAvgProfit(List<T> entities);

    long getAvgLifetime(List<T> entities, List<CloseInfoItem> closeInfoItems);
}
