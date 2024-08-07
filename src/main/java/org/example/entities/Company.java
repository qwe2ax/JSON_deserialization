package org.example.entities;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "Name",
        "Profit",
        "CreationDate",
        "ReportDate"
})
public class Company implements Comparable<Company> {

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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Profit")
    public String getProfit() {
        return profit;
    }

    @JsonProperty("Profit")
    public void setProfit(String profit) {
        this.profit = profit;
    }

    @JsonProperty("CreationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("CreationDate")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("ReportDate")
    public String getReportDate() {
        return reportDate;
    }

    @JsonProperty("ReportDate")
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profit='" + profit + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", reportDate='" + reportDate + '\''+ '}';
    }

    @Override
    public int compareTo(Company o) {
        return this.getReportDate().compareTo(o.reportDate);
    }

}