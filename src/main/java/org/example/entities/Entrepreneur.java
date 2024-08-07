package org.example.entities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "FirstName",
        "SecondName",
        "Profit",
        "CreationDate",
        "ReportDate"
})
public class Entrepreneur extends Company {

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

    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("FirstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("SecondName")
    public String getSecondName() {
        return secondName;
    }

    @JsonProperty("SecondName")
    public void setSecondName(String secondName) {
        this.secondName = secondName;
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
        return '[' + "firstName = " + firstName +
                ", reportDate = " + reportDate +
                ", creationDate = " + creationDate +
                ", profit = " + profit +
                ", secondName = " + secondName +
                ", firstName = " + firstName +
                ", id = " + id +
                ']';
    }

    @Override
    public int compareTo(Company o) {
        return this.getReportDate().compareTo(o.getReportDate());
    }

}