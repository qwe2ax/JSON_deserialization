package org.example.services.interfaces;

import org.example.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);
    Role getRoleById(int id);
    List<Role> getAllRoles();
    Role updateRole(Role role);
    void deleteRole(int id);
}
