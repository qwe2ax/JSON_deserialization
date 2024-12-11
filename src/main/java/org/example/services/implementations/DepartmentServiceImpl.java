package org.example.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dao.DepartmentRepository;
import org.example.entities.Department;
import org.example.services.interfaces.DepartmentService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User with id " + id + "not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional
    @Override
    public void updateDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    @Override
    public void deleteDepartmentById(int id) {
        departmentRepository.deleteById(id);
    }
}
