package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;
import org.example.services.DataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
