package org.example.services.implementations;

import lombok.RequiredArgsConstructor;
import org.example.dao.RoleRepository;
import org.example.entities.Role;
import org.example.services.interfaces.RoleService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role with id " + id + " not found"));
    }

    @Transactional(readOnly = true)

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }
}
