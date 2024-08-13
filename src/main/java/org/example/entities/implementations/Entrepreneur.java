package org.example.entities.Implementations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.example.entities.Interfaces.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "FirstName",
        "SecondName",
        "Profit",
        "CreationDate",
        "ReportDate"
})
public class Entrepreneur implements Creatable, DateComparable, Identifiable, Profitable, Reportable {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("SecondName")
    private String secondName;
    @JsonProperty("Profit")
    private String profit;
    @JsonProperty("CreationDate")
    private String creationDate;
    @JsonProperty("ReportDate")
    private String reportDate;

    @Override
    public String getReportDate() {
        return reportDate;
    }

    @Override
    public String getCreationDate() {
        return creationDate;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getProfit() {
        return profit;
    }

    @Override
    public <T extends Reportable> int compareDate(T o) {
        return this.getReportDate().compareTo(o.getReportDate());
    }
}