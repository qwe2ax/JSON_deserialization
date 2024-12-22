package org.example.services.interfaces;

import org.example.entities.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
    void deleteDepartmentById(int id);
}
