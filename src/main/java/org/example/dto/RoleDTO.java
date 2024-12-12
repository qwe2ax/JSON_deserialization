package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleDTO {
    private int id;
    private String name;
    private List<UserDTO> users;
}
