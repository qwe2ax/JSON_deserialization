package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDateTime dateOfRegistration;
    private int departmentId; // Например, можно передавать только ID департамента
    private List<Integer> roleIds; // Для ролей также можно передавать только список ID
}
