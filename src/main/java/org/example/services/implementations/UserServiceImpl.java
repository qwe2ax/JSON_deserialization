package org.example.services.implementations;

import lombok.RequiredArgsConstructor;
import org.example.dao.DepartmentRepository;
import org.example.dao.UserRepository;
import org.example.entities.Department;
import org.example.entities.User;
import org.example.services.interfaces.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void assignDepartmentToUser(int userId, int departmentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("No such user by id " + userId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("No such department by id " + departmentId));
        user.setDepartment(department);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void assignDepartmentToUser(int userId, String departmentName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("No such user by id " + userId));
        Department department = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new RuntimeException("No such department with name " + departmentName));
        user.setDepartment(department);
        userRepository.save(user);
    }


}