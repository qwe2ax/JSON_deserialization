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
        "CloseDate"
})
public class CloseInfoItem extends Company {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("CloseDate")
    private String closeDate;
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

    @JsonProperty("CloseDate")
    public String getCloseDate() {
        return closeDate;
    }

    @JsonProperty("CloseDate")
    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
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
        return "CloseInfoItem{" +
                "id='" + id + '\'' +
                ", closeDate='" + closeDate + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}