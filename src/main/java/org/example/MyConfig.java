package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;
import org.example.entities.implementations.Entrepreneur;
import org.example.services.DataService;
import org.example.services.ReportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("org.example")
public class MyConfig {


    @Bean
    public List<Company> companies(DataService dataService) {
        return dataService.getData("companies.json", new TypeReference<>() {});
    }

    @Bean
    public List<Entrepreneur> entrepreneurs(DataService dataService) {
        return dataService.getData("entrepreneurs.json", new TypeReference<>() {});
    }

    @Bean
    public List<CloseInfoItem> closeInfoItems(DataService dataService) {
        return dataService.getData("closeinfo.json", new TypeReference<>() {});
    }



}
