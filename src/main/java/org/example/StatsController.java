package org.example;

import org.example.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatsController {

    private final ReportService reportService;

    @Autowired
    public StatsController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return reportService.getStatistic();
    }
}
