package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "department", schema = "taskscheme")
@Entity
@Builder
public class Department {

    @Id
    @GeneratedValue
    private int id;


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @CreationTimestamp
    @Column(name = "date_of_creation", nullable = false)
    private LocalDate dateOfCreation;

    @Column(name = "location", nullable = false)
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<User> users;

}
