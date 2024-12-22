package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DepartmentDTO {
    private int id;
    private String name;
    private String location;
    private LocalDate dateOfCreation;
    private List<Integer> userIds;
}
