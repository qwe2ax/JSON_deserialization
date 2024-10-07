package org.example.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.example.entities.interfaces.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "Name",
        "Profit",
        "CreationDate",
        "ReportDate"
})
public class Company implements Creatable, DateComparable, Profitable, Reportable, Identifiable {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Profit")
    private String profit;
    @JsonProperty("CreationDate")
    private String creationDate;
    @JsonProperty("ReportDate")
    private String reportDate;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public String getReportDate() {
        return reportDate;
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