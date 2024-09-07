package org.example;


import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.implementations.CloseInfoItem;
import org.example.entities.implementations.Company;
import org.example.entities.implementations.Entrepreneur;
import org.example.services.DataService;
import org.example.services.ReportService;
import org.example.services.companies.CompaniesDataFilter;
import org.example.services.entrepreneurs.EntrepreneursDataFilter;
import org.example.services.interfaces.DataFilter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MyConfig.class);
        ReportService reportService = context.getBean("reportService", ReportService.class);
        reportService.printAnalytic(context);
        context.close();
    }
}