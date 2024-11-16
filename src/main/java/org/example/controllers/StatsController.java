package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.AnalyticResponseDTO;
import org.example.services.implementations.ReportServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class StatsController {

    private final ReportServiceImpl reportService;

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return reportService.getStatistic();
    }

    @GetMapping("/analytic")
    public AnalyticResponseDTO getAnalytic() {
        return reportService.getAnalytic();
    }

    @PostMapping("/analytic")
    public AnalyticResponseDTO createAnalytic(@RequestBody AnalyticResponseDTO analyticData) {

        System.out.println("Полученные данные аналитики: " + analyticData);

        return analyticData;
    }

}
