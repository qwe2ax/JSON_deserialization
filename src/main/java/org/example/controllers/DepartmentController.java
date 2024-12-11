package org.example.controllers;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.dto.DepartmentDTO;
import org.example.entities.Department;
import org.example.services.EntityConverterService;
import org.example.services.interfaces.DepartmentService;
import org.springframework.data.convert.EntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EntityConverterService entityConverterService;

    @GetMapping()
    public List<DepartmentDTO> read() {
        return departmentService.getAllDepartments()
                .stream()
                .map(entityConverterService::convertDepartmentToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public DepartmentDTO readById(@PathVariable("id") int id) {
        return entityConverterService.convertDepartmentToDTO(departmentService.getDepartmentById(id));
    }

    @PostMapping()
    public ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentDTO departmentDTO) {
        Department savedDepartment = departmentService.saveDepartment(
                entityConverterService.convertToDepartment(departmentDTO));
        return new ResponseEntity<>(entityConverterService.convertDepartmentToDTO(savedDepartment), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable("id") int id, @RequestBody DepartmentDTO departmentDTO) {
        departmentDTO.setId(id);
        Department department = departmentService.saveDepartment(entityConverterService.convertToDepartment(departmentDTO));
        return new ResponseEntity<>(entityConverterService.convertDepartmentToDTO(department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
