package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;
import org.example.services.AnalyticService;
import org.example.services.DataService;
import org.example.services.InitService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.services.InitService.*;

public class Main {

    public static String entrepreneursPath = "entrepreneurs.json";
    public static String companiesPath = "companies.json";
    public static String closedPath = "closeinfo.json";

    public static void main(String[] args) {
        InitService.init();
        AnalyticService.printAnalytic();
    }
}