package org.example.entities.implementations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.example.entities.interfaces.Closeable;
import org.example.entities.interfaces.Identifiable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "CloseDate"
})
public class CloseInfoItem implements Identifiable, Closeable {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("CloseDate")
    private String closeDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCloseDate() {
        return closeDate;
    }
}
