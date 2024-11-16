package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.Department;
import org.example.services.interfaces.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping()
    public List<Department> read() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department readById(@PathVariable("id") int id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping()
    public void create(@RequestBody Department department) {
        departmentService.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Department department) {
        department.setId(id);
        departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        departmentService.deleteDepartmentById(id);
    }

}
