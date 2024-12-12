package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dao.DepartmentRepository;
import org.example.dao.RoleRepository;
import org.example.dao.UserRepository;
import org.example.dto.DepartmentDTO;
import org.example.dto.RoleDTO;
import org.example.dto.UserDTO;
import org.example.entities.Department;
import org.example.entities.Role;
import org.example.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class EntityConverterService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDTO convertUserToDTO (User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .dateOfRegistration(user.getDateOfRegistration())
                .departmentId(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .roleIds(user.getRoles() != null ? user.getRoles().stream()
                        .map(Role::getId)
                        .toList() : null)
                .build();
    }

    public User convertToUser (UserDTO userDTO) {
        Department department = departmentRepository.findById(userDTO.getDepartmentId()).orElse(null);
        List<Role> roles = roleRepository.findAllById(userDTO.getRoleIds());

        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .dateOfBirth(userDTO.getDateOfBirth())
                .dateOfRegistration(userDTO.getDateOfRegistration())
                .department(department)
                .roles(roles)
                .build();
    }

    public DepartmentDTO convertDepartmentToDTO(Department department) {
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .location(department.getLocation())
                .dateOfCreation(department.getDateOfCreation())
                .userIds(department.getUsers() != null ?
                        department.getUsers().stream()
                                .map(User::getId)
                                .toList()
                        : null)
                .build();
    }

    public Department convertToDepartment(DepartmentDTO departmentDTO) {
        List<User> users = userRepository.findAllById(departmentDTO.getUserIds());

        return Department.builder()
                .id(departmentDTO.getId())
                .name(departmentDTO.getName())
                .location(departmentDTO.getLocation())
                .dateOfCreation(departmentDTO.getDateOfCreation())
                .users(users)
                .build();
    }

    public RoleDTO convertRoleToDTO(Role role) {
        List<UserDTO> usersDTO = role.getUsers().stream().map(this::convertUserToDTO).toList();
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .users(usersDTO.isEmpty() ? null : usersDTO)
                .build();
    }

    public Role convertToRole(RoleDTO roleDTO) {
        List<User> users = roleDTO.getUsers().stream().map(this::convertToUser).toList();

        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .users(users)
                .build();
    }


}
