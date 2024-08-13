package org.example;

import org.example.services.AnalyticService;
import org.example.services.InitService;

public class Main {

    public static final String ENTREPRENEURS_PATH = "entrepreneurs.json";
    public static final String companiesPath = "companies.json";
    public static final String closedPath = "closeinfo.json";

    public static void main(String[] args) {
        InitService.init();
        AnalyticService.printAnalytic();
    }
}