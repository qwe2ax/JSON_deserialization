package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.DepartmentDTO;
import org.example.dto.RoleDTO;
import org.example.entities.Department;
import org.example.entities.Role;
import org.example.services.EntityConverterService;
import org.example.services.interfaces.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final EntityConverterService entityConverterService;


    @GetMapping
    public List<RoleDTO> read() {
        return roleService.getAllRoles()
                .stream()
                .map(entityConverterService::convertRoleToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public RoleDTO readById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        return entityConverterService.convertRoleToDTO(role);
    }

    @PostMapping()
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO) {
        Role role = entityConverterService.convertToRole(roleDTO);
        roleService.saveRole(role);
        return new ResponseEntity<>(entityConverterService.convertRoleToDTO(role), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable("id") int id, @RequestBody RoleDTO roleDTO) {
        roleDTO.setId(id);
        Role role = entityConverterService.convertToRole(roleDTO);
        roleService.updateRole(role);
        return new ResponseEntity<>(entityConverterService.convertRoleToDTO(role), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
